package com.watsoo.sfa.trial.scheduler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.watsoo.sfa.trial.dto.SendEmailRequest;
import com.watsoo.sfa.trial.model.TrialCompany;
import com.watsoo.sfa.trial.model.TrialUserDetails;
import com.watsoo.sfa.trial.model.Transaction;
import com.watsoo.sfa.trial.repository.TrailCompanyRepository;
import com.watsoo.sfa.trial.repository.TransactionRepository;
import com.watsoo.sfa.trial.repository.TrialUserDetailsRepository;
import com.watsoo.sfa.trial.service.EmailService;

@Component
public class Scheduler {

	@Autowired
	private TrailCompanyRepository companyRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private TrialUserDetailsRepository trialUserDetailsRepository;

	@Autowired
	private EmailService emailService;

	@Scheduled(cron = "00 00 18 * * *", zone = "UTC")
	public void sendCompanyCredentialsExpiry() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String expiryDate = dateFormat.format(new Date()); // "2023-04-11"; //

		List<TrialCompany> allExpiryCompanyData = companyRepository.findByExpiryDate(expiryDate);
		List<Transaction> allTransactionExpireData = transactionRepository.findByEndDate(expiryDate);
		List<TrialCompany> updateCompanyDetails = allExpiryCompanyData.stream()
				.map(e -> new TrialCompany(e.getId(), e.getCompanyIdentifier(), e.getAdminEmail(), null,
						e.getUserEmail(), null, null, null, true, null, e.getCreatedBy(), e.getCreatedOn(),
						e.getUpdatedBy(), e.getUpdatedOn()))
				.collect(Collectors.toList());

		List<Transaction> updateTransactions = allTransactionExpireData.stream()
				.map(e -> new Transaction(e.getId(), e.getCompanyId(), e.getAssignedBy(), e.getStartDate(),
						e.getEndDate(), e.getCompanyName(), e.getClientEmail(), false))
				.collect(Collectors.toList());

		List<Long> companyIds = allExpiryCompanyData.stream().map(e -> e.getId()).collect(Collectors.toList());
		List<TrialUserDetails> allExpiredTrailUsers = trialUserDetailsRepository.findByCompanyId(companyIds);

		List<TrialUserDetails> updateTrialUserDetailsList = new ArrayList<>();

		for (TrialUserDetails trialUserDetails : allExpiredTrailUsers) {

			trialUserDetails.setPassword(null);
			trialUserDetails.setUpdatedOn(new Date());
			updateTrialUserDetailsList.add(trialUserDetails);

		}

		companyRepository.saveAll(updateCompanyDetails);
		transactionRepository.saveAll(updateTransactions);
		trialUserDetailsRepository.saveAll(updateTrialUserDetailsList);

		if (updateTransactions.size() > 0) {

			for (Transaction transaction : updateTransactions) {

				String[] toEmail = { transaction.getClientEmail() };
				SendEmailRequest emailRequest = new SendEmailRequest();
				emailRequest.setToEmailIds(toEmail);
				emailRequest.setSubject("Trail Credentials Expired");

				StringBuilder sb = new StringBuilder();
				sb.append("<html><body>");
				sb.append("<p>Dear " + transaction.getCompanyName() + ",</p>"
						+ "<p>This mail is to inform you that your trail is expired today.</p>");

				sb.append("<p>We kindly request you to take a full subscription of the application. \n" + "<br>"
						+ "For more details contact with our sales team." + "<br>"
						+ "Thank you for your co-operation.</p>");
				sb.append("</body></html>");

				emailRequest.setContent(sb.toString());
				emailService.sendMail(emailRequest);
			}
		}

	}

}
