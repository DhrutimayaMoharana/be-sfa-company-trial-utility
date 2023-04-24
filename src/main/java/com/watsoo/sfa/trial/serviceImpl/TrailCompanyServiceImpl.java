package com.watsoo.sfa.trial.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.watsoo.sfa.trial.dto.TrialCompanyDto;
import com.watsoo.sfa.trial.dto.TrialCompanyRequestDto;
import com.watsoo.sfa.trial.dto.CompanyDto;
import com.watsoo.sfa.trial.dto.Response;
import com.watsoo.sfa.trial.dto.SendEmailRequest;
import com.watsoo.sfa.trial.enums.UserType;
import com.watsoo.sfa.trial.model.TrialCompany;
import com.watsoo.sfa.trial.model.Configuration;
import com.watsoo.sfa.trial.model.Transaction;
import com.watsoo.sfa.trial.model.TrialUserDetails;
import com.watsoo.sfa.trial.model.UserData;
import com.watsoo.sfa.trial.repository.TrailCompanyRepository;
import com.watsoo.sfa.trial.repository.ConfigurationRepository;
import com.watsoo.sfa.trial.repository.TransactionRepository;
import com.watsoo.sfa.trial.repository.TrialUserDetailsRepository;
import com.watsoo.sfa.trial.service.TrailCompanyService;
import com.watsoo.sfa.trial.service.EmailService;
import com.watsoo.sfa.trial.service.TransactionService;
import com.watsoo.sfa.trial.util.GenerateRandomCode;
import com.watsoo.sfa.trial.util.Pagination;

@Service
public class TrailCompanyServiceImpl implements TrailCompanyService {

	@Autowired
	private TrailCompanyRepository companyRepository;

	@Autowired
	private ConfigurationRepository configurationRepository;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private TrialUserDetailsRepository trialUserDetailsRepository;

	@Autowired
	private EmailService emailService;

	@Override
	public Response<?> saveCompany(TrialCompanyDto companyDto) {
		try {
			if (companyDto != null && companyDto.getId() == null) {
				if (companyDto.getCompanyIdentifier() == null || companyDto.getCompanyIdentifier().isEmpty()) {
					return new Response<>(HttpStatus.BAD_REQUEST.value(), "Company Identifier is required", null);
				}

				if (companyDto.getTrialsUserDetailsDto() == null) {
					return new Response<>(HttpStatus.BAD_REQUEST.value(),
							"Company admin email and user email required");
				}
				if (companyDto.getTrialsUserDetailsDto() != null) {
					if (companyDto.getTrialsUserDetailsDto().getAdminEmail() == null
							|| companyDto.getTrialsUserDetailsDto().getAdminEmail().isEmpty()) {
						return new Response<>(HttpStatus.BAD_REQUEST.value(), "Admin email is required", null);
					}
					if (companyDto.getTrialsUserDetailsDto().getUserEmail() == null
							|| companyDto.getTrialsUserDetailsDto().getUserEmail().length == 0) {
						return new Response<>(HttpStatus.BAD_REQUEST.value(), "User email is required", null);
					}
					if (companyDto.getTrialsUserDetailsDto().getUserEmail() != null
							&& companyDto.getTrialsUserDetailsDto().getUserEmail().length != 0) {
						for (String userEmail : companyDto.getTrialsUserDetailsDto().getUserEmail()) {
							if (companyDto.getTrialsUserDetailsDto().getAdminEmail() != null
									&& companyDto.getTrialsUserDetailsDto().getAdminEmail().equals(userEmail)) {
								return new Response<>(HttpStatus.BAD_REQUEST.value(),
										"User email and admin email cannot be same", null);
							}
							if (companyDto.getTrialsUserDetailsDto().getAdminEmail() != null
									&& companyDto.getTrialsUserDetailsDto().getAdminEmail().equals(userEmail)) {
								return new Response<>(HttpStatus.BAD_REQUEST.value(),
										"User email and admin email cannot be same", null);
							}
						}
						Set<String> uniqueUserEmails = new HashSet<>(
								Arrays.asList(companyDto.getTrialsUserDetailsDto().getUserEmail()));
						if (companyDto.getTrialsUserDetailsDto().getUserEmail().length > uniqueUserEmails.size()) {
							return new Response<>(HttpStatus.BAD_REQUEST.value(), "User email cannot be duplicate",
									null);
						}
					}
				}

				TrialCompany company = companyRepository.findByCompanyIdentifier(companyDto.getCompanyIdentifier());
				if (company != null) {
					return new Response<>(HttpStatus.BAD_REQUEST.value(), "Company Identifier cannot be duplicate.",
							null);
				}

				List<TrialUserDetails> trailUserDataInDatabase = trialUserDetailsRepository
						.findByAdminEmailAndUserEmail(companyDto.getTrialsUserDetailsDto().getAdminEmail(),
								companyDto.getTrialsUserDetailsDto().getUserEmail());
				if (trailUserDataInDatabase.size() > 0) {
					return new Response<>(HttpStatus.BAD_REQUEST.value(),
							"Admin email or User email is already used by other company", null);
				}

				TrialCompany companySave = companyDto.convertToCompany();
				TrialCompanyDto companySaveResponse = companyRepository.save(companySave).convertToCompanyDto();

				List<TrialUserDetails> trialUserDetailListForSave = new ArrayList<>();

				trialUserDetailListForSave
						.add(new TrialUserDetails(null, "admin", companyDto.getTrialsUserDetailsDto().getAdminEmail(),
								null, true, UserType.ADMIN.name(), new UserData(companyDto.getCreatedBy().getId()),
								new Date(), null, null, new TrialCompany(companySaveResponse.getId())));

				for (String userEmail : companyDto.getTrialsUserDetailsDto().getUserEmail()) {
					trialUserDetailListForSave.add(new TrialUserDetails(null, "user", userEmail, null, true,
							UserType.USER.name(), new UserData(companyDto.getCreatedBy().getId()), new Date(), null,
							null, new TrialCompany(companySaveResponse.getId())));
				}

				trialUserDetailsRepository.saveAll(trialUserDetailListForSave);

				return new Response<>(HttpStatus.CREATED.value(), "Company added successfully", companySaveResponse);
			} else {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Invalid Input", null);
			}
		} catch (Exception e) {
			return new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
		}
	}

	@Override
	public Response<?> updateCompany(TrialCompanyDto companyDto) {
		try {
			if (companyDto != null && companyDto.getId() != null) {
				Optional<TrialCompany> companyById = companyRepository.findById(companyDto.getId().longValue());
				TrialCompany companyUpdate = companyById.get();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				if (companyDto.getUsedBy() == null || companyDto.getUsedBy().getId() == null) {
					return new Response<>(HttpStatus.BAD_REQUEST.value(), "Used by is required", null);
				}
				if (companyDto.getClientEmail() == null || companyDto.getClientEmail().isEmpty()) {
					return new Response<>(HttpStatus.BAD_REQUEST.value(), "Client email is required", null);
				}
				if (companyDto.getExpiryDate() == null) {

					Configuration configuration = configurationRepository.findByConfigurationKey("TRIAL_EXPIRY_DAY");
					Date todayDate = new Date();
					Calendar cal = Calendar.getInstance();
					try {
						cal.setTime(dateFormat.parse(dateFormat.format(todayDate)));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					cal.add(Calendar.DAY_OF_MONTH, Integer.parseInt(configuration.getValue()));
					companyUpdate.setExpiryDate(dateFormat.parse(dateFormat.format(cal.getTime())));

				} else {
					if (companyDto.getExpiryDate().getTime() < dateFormat.parse(dateFormat.format(new Date()))
							.getTime()) {
						return new Response<>(HttpStatus.BAD_REQUEST.value(),
								"The expiration date cannot be a past date", null);
					}

					companyUpdate.setExpiryDate(companyDto.getExpiryDate());
				}

				List<TrialCompany> comanyDataInDatabase = companyRepository.findByAdminEmailAndUserEmailAndId(
						companyDto.getAdminEmail(), companyDto.getUserEmail(), companyDto.getId());
				if (comanyDataInDatabase.size() > 0) {
					return new Response<>(HttpStatus.BAD_REQUEST.value(),
							"Admin email or User email is already used by other company", null);
				}

				List<TrialCompany> allCompanyByUsedBy = companyRepository
						.getAllByUsedBy(companyDto.getUsedBy() != null && companyDto.getUsedBy().getId() != null
								? companyDto.getUsedBy().getId()
								: null);
				Configuration getMinimumFreeTrialCompany = configurationRepository
						.findByConfigurationKey("MIN_FREE_TRIAL_COMPANY");
				if (allCompanyByUsedBy.size() >= Integer.parseInt(getMinimumFreeTrialCompany.getValue())) {
					return new Response<>(HttpStatus.BAD_REQUEST.value(),
							"You are not allow to use more trial company.");
				}

				String adminPassword = GenerateRandomCode.randomString(8);
				String userPassword = GenerateRandomCode.randomString(8);
				companyUpdate.setUsedBy(new UserData(companyDto.getUsedBy().getId()));
				companyUpdate.setUpdatedBy(companyDto.getUpdatedBy());
				companyUpdate.setUpdatedOn(new Date());

				String firstPartOfToken = "";

				List<TrialUserDetails> trialUserDetailListUpdate = new ArrayList<>();

				for (TrialUserDetails trialUserDetails : companyById.get().getTrialUserDetails()) {
					if (trialUserDetails.getUserType().equals(UserType.ADMIN.name())) {
						trialUserDetails.setPassword(adminPassword);
					} else {
						trialUserDetails.setPassword(userPassword);
					}
					trialUserDetails.setUpdatedBy(companyDto.getUpdatedBy());
					trialUserDetails.setUpdatedOn(new Date());
					trialUserDetailListUpdate.add(trialUserDetails);
					firstPartOfToken = firstPartOfToken + trialUserDetails.getEmail() + "||"
							+ trialUserDetails.getPassword() + "||";
				}

				Transaction transactionInDB = transactionRepository.findByCompanyIdAndIsAcive(companyUpdate.getId(),
						true);
				if (transactionInDB != null) {
					return new Response<>(HttpStatus.BAD_REQUEST.value(), "Company already usedby another user", null);
				}
				Transaction transaction = new Transaction(null, companyUpdate,
						companyDto.getUpdatedBy() != null ? new UserData(companyDto.getUpdatedBy()) : new UserData(1L),
						dateFormat.parse(dateFormat.format(new Date())), companyUpdate.getExpiryDate(),
						companyDto.getClientName(), companyDto.getClientEmail(), true);

				String token = firstPartOfToken + companyUpdate.getExpiryDate().getTime();

				companyUpdate.setToken(Base64.getEncoder().encodeToString(token.getBytes()));

				companyDto = companyRepository.save(companyUpdate).convertToCompanyDto();

				transactionService.transactionAdd(transaction);

				trialCredentialSet(companyUpdate, transaction, trialUserDetailListUpdate);

				return new Response<>(HttpStatus.OK.value(), "Company added successfully", companyDto);
			} else {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Invalid Input", null);
			}
		} catch (Exception e) {
			return new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
		}
	}

	@Override
	public Response<?> getByIdCompany(Long id) {
		try {
			Optional<TrialCompany> companyById = companyRepository.findById(id);
			if (companyById.isPresent()) {
				TrialCompanyDto companyDto = companyById.get().convertToCompanyDto();
				if (companyDto.getUsedBy() != null) {
					Transaction transaction = transactionRepository.findByCompanyIdAndIsAcive(id, true);
					companyDto.setClientEmail(transaction.getClientEmail());
					companyDto.setClientName(transaction.getCompanyName());
				}
				return new Response<>(HttpStatus.OK.value(), HttpStatus.OK.name(), companyDto);
			} else {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Invalid Input", null);
			}
		} catch (Exception e) {
			return new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
		}
	}

	@Override
	public Response<?> getAllCompany(int pageNo, int pageSize, TrialCompanyDto companyDto) {
		try {
			Pagination<List<?>> pagination = new Pagination<>();
			Pageable pageRequest = Pageable.unpaged();
			if (pageSize > 0) {
				pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("id").descending());
			}
			Page<TrialCompany> companyList = companyRepository.findAll(companyDto, pageRequest);
			List<TrialCompany> companyAll = companyList.getContent();

			List<TrialCompanyDto> companyDtoList = companyAll.stream().map(e -> e.convertToCompanyDto())
					.collect(Collectors.toList());

			pagination.setData(companyDtoList);
			pagination.setNumberOfElements(companyList.getNumberOfElements());
			pagination.setTotalElements(companyList.getTotalElements());
			pagination.setTotalPages(companyList.getTotalPages());

			return new Response<>(HttpStatus.OK.value(), HttpStatus.OK.name(), pagination);

		} catch (Exception e) {
			return new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
		}
	}

	public void trialCredentialSet(TrialCompany company, Transaction transaction,
			List<TrialUserDetails> trialUserDetailListUpdate) {

		String[] toEmails = { transaction.getClientEmail() };

		SendEmailRequest emailRequest = new SendEmailRequest();
		emailRequest.setToEmailIds(toEmails);
		emailRequest.setSubject("Thank you for Showing Your Interest in NYGGS SFA!");

		StringBuilder sb = new StringBuilder();
		sb.append("<html><body>");
		sb.append("<h1>Thank You! You Free Trial Credentials are Here!</h1>");
		sb.append("<p>Dear " + transaction.getCompanyName() + " ,</p>"
				+ "<p>Thank you for showing your interest in NYGGS SFA! We are excited you are considering our platform for your business needs."
				+ "<br>"
				+ "To get you started with the free trial period, we have provided you with the following trial user/admin credentials:</p>");
		sb.append("<table style='border: 2px solid black;'>");
		sb.append(
				"<tr><th style='padding:10px; text-align:center; border: 2px solid black; '>SL. NO.</th><th style='padding:10px; text-align:center; border: 2px solid black; '>User Type</th><th style='padding:10px; text-align:center; border: 2px solid black; '>Email</th><th style='padding:10px; text-align:center; border: 2px solid black; '>Password</th><th style='padding:10px; text-align:center; border: 2px solid black; '>Expiry On</th></tr>");

		int i = 1;
		for (TrialUserDetails trialUserDetails : trialUserDetailListUpdate) {

			sb.append("<tr>");
			sb.append("<td style='padding:10px; text-align:center; border: 2px solid black; '>" + i + "</td>");
			sb.append("<td style='padding:10px; text-align:center; border: 2px solid black; '>"
					+ trialUserDetails.getUserType() + "</td>");
			sb.append("<td style='padding:10px; text-align:center; border: 2px solid black; '>"
					+ trialUserDetails.getEmail() + "</td>");
			sb.append("<td style='padding:10px; text-align:center; border: 2px solid black; '>"
					+ trialUserDetails.getPassword() + "</td>");
			sb.append("<td style='padding:10px; text-align:center; border: 2px solid black; '>"
					+ company.getExpiryDate() + "</td>");
			sb.append("</tr>");
			i++;
		}

		sb.append("</table>");
		sb.append(
				"<p>We encourage you to take a few moments to explore the platform and get familiar with its features. If you have any questions or need assistance, please don’t hesitate to reach out to our support team.</p>");
		sb.append("<p>We look forward to working with you!" + "</p>" + "<p>Sincerely," + "<br>" + "Team NYGGS.</p>");
		sb.append("</body></html>");

		emailRequest.setContent(sb.toString());
		emailService.sendMail(emailRequest);

	}

	@Override
	public Response<?> addTrialCompanyV1(TrialCompanyRequestDto companyDto) {
		try {
			if (companyDto.getNoOfUsers() == null || companyDto.getNoOfUsers() == 0) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide no. of users.");
			}
			if (companyDto.getCreatedBy() == null || companyDto.getCreatedBy() == 0) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide createdBy.");
			}
			if (companyDto.getUsedBy() == null || companyDto.getUsedBy() == 0) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide useddBy.");
			}
			if (companyDto.getClientName() == null || companyDto.getClientName().isEmpty()) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide valid client name.");
			}
			if (companyDto.getClientEmail() == null || companyDto.getClientEmail().isEmpty()) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide valid client email.");
			}
			List<TrialCompany> allCompanyByUsedBy = companyRepository.getAllByUsedBy(companyDto.getUsedBy());
			Configuration getMinimumFreeTrialCompany = configurationRepository
					.findByConfigurationKey("MIN_FREE_TRIAL_COMPANY");
			if (allCompanyByUsedBy.size() >= Integer.parseInt(getMinimumFreeTrialCompany.getValue())) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "You are not allow to use more trial company.");
			}
			Configuration getMinimumTrailUser = configurationRepository.findByConfigurationKey("MIN_TRIAL_USER");
			if (companyDto.getNoOfUsers() > Integer.parseInt(getMinimumTrailUser.getValue())) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Max 5 users allow for trial.");
			}

			Long countCompany = companyRepository.count();

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			TrialCompany companyToSave = new TrialCompany();

			companyToSave.setCompanyIdentifier("TRCMP" + (countCompany + 1));
			companyToSave.setCreatedBy(new UserData(companyDto.getCreatedBy()));
			companyToSave.setUsedBy(new UserData(companyDto.getUsedBy()));
			companyToSave.setIsActive(true);

			if (companyDto.getExpiryDate() == null) {

				Configuration minimumExpiryDay = configurationRepository.findByConfigurationKey("TRIAL_EXPIRY_DAY");
				Date todayDate = new Date();
				Calendar cal = Calendar.getInstance();
				try {
					cal.setTime(dateFormat.parse(dateFormat.format(todayDate)));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				cal.add(Calendar.DAY_OF_MONTH, Integer.parseInt(minimumExpiryDay.getValue()));
				companyToSave.setExpiryDate(dateFormat.parse(dateFormat.format(cal.getTime())));

			} else {
				if (companyDto.getExpiryDate().getTime() < dateFormat.parse(dateFormat.format(new Date())).getTime()) {
					return new Response<>(HttpStatus.BAD_REQUEST.value(), "The expiration date cannot be a past date",
							null);
				}

				companyToSave.setExpiryDate(companyDto.getExpiryDate());
			}

			companyToSave.setCreatedOn(new Date());

			List<TrialUserDetails> trialUserDetailListForSave = new ArrayList<>();

			String adminEmail = companyToSave.getCompanyIdentifier().toLowerCase() + "adm1@watsoo.com";
			String adminName = (companyToSave.getCompanyIdentifier() + "adm1").toUpperCase();

			String firstPartOfToken = "";
			String adminPassword = GenerateRandomCode.randomString(8);
			String userPassword = GenerateRandomCode.randomString(8);

			trialUserDetailListForSave.add(new TrialUserDetails(null, adminName, adminEmail, adminPassword, true,
					UserType.ADMIN.name(), new UserData(companyDto.getCreatedBy()), new Date(), null, null, null));

			firstPartOfToken = firstPartOfToken + adminEmail + "||" + adminPassword + "||";

			String userEmail = companyToSave.getCompanyIdentifier().toLowerCase() + "usr";
			String userName = (companyToSave.getCompanyIdentifier() + "usr").toUpperCase();

			for (int i = 1; i <= companyDto.getNoOfUsers(); i++) {
				TrialUserDetails userDetails = new TrialUserDetails(null, userName + i, userEmail + i + "@watsoo.com",
						userPassword, true, UserType.USER.name(), new UserData(companyDto.getCreatedBy()), new Date(),
						null, null, null);

				firstPartOfToken = firstPartOfToken + userEmail + i + "@watsoo.com" + "||" + userPassword + "||";

				trialUserDetailListForSave.add(userDetails);
			}

			String token = firstPartOfToken + companyToSave.getExpiryDate().getTime();

			companyToSave.setToken(Base64.getEncoder().encodeToString(token.getBytes()));

			TrialCompanyDto companySaveResponseDto = companyRepository.save(companyToSave).convertToCompanyDto();

			for (TrialUserDetails trialUserDetails : trialUserDetailListForSave) {
				trialUserDetails.setCompanyId(new TrialCompany(companySaveResponseDto.getId()));
			}

			Transaction transaction = new Transaction(null, new TrialCompany(companySaveResponseDto.getId()),
					companyDto.getCreatedBy() != null ? new UserData(companyDto.getCreatedBy()) : new UserData(1L),
					dateFormat.parse(dateFormat.format(new Date())), companySaveResponseDto.getExpiryDate(),
					companyDto.getClientName(), companyDto.getClientEmail(), true);

			transactionService.transactionAdd(transaction);

			trialUserDetailsRepository.saveAll(trialUserDetailListForSave);

			trialCredentialSet(companyToSave, transaction, trialUserDetailListForSave);

			return new Response<>(HttpStatus.CREATED.value(), "Trial company created and assign succefully",
					companySaveResponseDto);

		} catch (Exception e) {
			return new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
		}
	}

	@Override
	public Response<?> addTrialCompanyV2(TrialCompanyRequestDto companyDto) {
		try {
			if (companyDto.getNoOfUsers() == null || companyDto.getNoOfUsers() == 0) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide no. of users.");
			}
			if (companyDto.getCreatedBy() == null || companyDto.getCreatedBy() == 0) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Please provide createdBy.");
			}

			List<TrialCompany> allCompanyByCreatedBy = companyRepository.getAllByCreatedBy(companyDto.getCreatedBy());
			Configuration getMinimumFreeTrialCompany = configurationRepository
					.findByConfigurationKey("MIN_FREE_TRIAL_COMPANY");
			if (allCompanyByCreatedBy.size() >= Integer.parseInt(getMinimumFreeTrialCompany.getValue())) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(),
						"You are not allow to create more trial company.");
			}
			Configuration getMinimumTrailUser = configurationRepository.findByConfigurationKey("MIN_TRIAL_USER");
			if (companyDto.getNoOfUsers() > Integer.parseInt(getMinimumTrailUser.getValue())) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Max 5 users allow for trial.");
			}

			Long countCompany = companyRepository.count();

			TrialCompany companyToSave = new TrialCompany();

			companyToSave.setCompanyIdentifier("TRCMP" + (countCompany + 1));
			companyToSave.setCreatedBy(new UserData(companyDto.getCreatedBy()));
			companyToSave.setIsActive(true);

			companyToSave.setCreatedOn(new Date());

			List<TrialUserDetails> trialUserDetailListForSave = new ArrayList<>();

			String adminEmail = companyToSave.getCompanyIdentifier().toLowerCase() + "adm1@watsoo.com";
			String adminName = (companyToSave.getCompanyIdentifier() + "adm1").toUpperCase();

			trialUserDetailListForSave.add(new TrialUserDetails(null, adminName, adminEmail, null, true,
					UserType.ADMIN.name(), new UserData(companyDto.getCreatedBy()), new Date(), null, null, null));

			String userEmail = companyToSave.getCompanyIdentifier().toLowerCase() + "usr";
			String userName = (companyToSave.getCompanyIdentifier() + "usr").toUpperCase();

			for (int i = 1; i <= companyDto.getNoOfUsers(); i++) {
				TrialUserDetails userDetails = new TrialUserDetails(null, userName + i, userEmail + i + "@watsoo.com",
						null, true, UserType.USER.name(), new UserData(companyDto.getCreatedBy()), new Date(), null,
						null, null);

				trialUserDetailListForSave.add(userDetails);
			}

			TrialCompanyDto companySaveResponseDto = companyRepository.save(companyToSave).convertToCompanyDto();

			for (TrialUserDetails trialUserDetails : trialUserDetailListForSave) {
				trialUserDetails.setCompanyId(new TrialCompany(companySaveResponseDto.getId()));
			}

			trialUserDetailsRepository.saveAll(trialUserDetailListForSave);

			return new Response<>(HttpStatus.CREATED.value(), "Trial company created succefully",
					companySaveResponseDto);

		} catch (Exception e) {
			return new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
		}
	}

}
