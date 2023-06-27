package com.watsoo.sfa.trial.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.watsoo.sfa.trial.constant.Constant;
import com.watsoo.sfa.trial.dto.CompanyDto;
import com.watsoo.sfa.trial.dto.LoginRequestDto;
import com.watsoo.sfa.trial.dto.LoginResponseDto;
import com.watsoo.sfa.trial.dto.Response;
import com.watsoo.sfa.trial.dto.TrialUserPermissionDto;
import com.watsoo.sfa.trial.dto.UserDataDto;
import com.watsoo.sfa.trial.dto.UserPermissionDto;
import com.watsoo.sfa.trial.enums.UserType;
import com.watsoo.sfa.trial.model.Transaction;
import com.watsoo.sfa.trial.model.TrialUserDetails;
import com.watsoo.sfa.trial.model.UserData;
import com.watsoo.sfa.trial.repository.TransactionRepository;
import com.watsoo.sfa.trial.repository.TrialUserDetailsRepository;
import com.watsoo.sfa.trial.service.TrialUserDetailsService;

@Service
public class TrialUserDetailsServiceImpl implements TrialUserDetailsService {

	@Autowired
	private TrialUserDetailsRepository trialUserDetailsRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public LoginResponseDto trialUserLogin(LoginRequestDto loginRequestDto) {

		if (loginRequestDto != null) {
			
			if(loginRequestDto.getIdentifier()==null || loginRequestDto.getIdentifier().isEmpty()) {
				throw new RuntimeException("Invalid Identifier");
			}
			
			TrialUserDetails userData = trialUserDetailsRepository
					.getTrialUserDetailsByEmail(loginRequestDto.getEmail(),loginRequestDto.getIdentifier());
			if (userData != null) {
				if (userData.getPassword() != null && !userData.getPassword().isEmpty()
						&& userData.getPassword().equals(loginRequestDto.getPassword())) {

//					Transaction transaction = transactionRepository
//							.findByCompanyIdAndIsAcive(userData.getCompanyId().getId(), true);

					LoginResponseDto loginResponseDto = new LoginResponseDto(userData.getId(), null, userData.getName(),
							userData.getEmail(), null, null, userData.getCompanyId().getId(),
							new CompanyDto(userData.getCompanyId().getId(),
									userData.getCompanyId().getCompanyIdentifier(), null, null, null, null,
									userData.getCompanyId().getIsActive(), userData.getCompanyId().getCreatedOn(),
									userData.getCompanyId().getCreatedBy().getId()),
							null, null, null, null, null, null, null, null, null, null, null, null, null, null);

					return loginResponseDto;
				} else {
					throw new RuntimeException("Invalid Credentials");
				}
			} else {
				throw new RuntimeException("Invalid Credentials");
			}
		} else {
			throw new RuntimeException("Invalid Credentials");
		}
	}

	@Override
	public Response<?> userPermissionResposneById(Long id) {

		Optional<TrialUserDetails> findByTrialUsed = trialUserDetailsRepository.findById(id);

		TrialUserPermissionDto userPermissionDto = new TrialUserPermissionDto(
				findByTrialUsed.get().getUserType().equalsIgnoreCase(UserType.ADMIN.name())
						? findByTrialUsed.get().getCompanyId().getId()
						: null,
				findByTrialUsed.get().getUserType().equalsIgnoreCase(UserType.USER.name())
						? Arrays.asList(findByTrialUsed.get().getId())
						: new ArrayList<>());

		return new Response<>(HttpStatus.OK.value(), "Success", userPermissionDto);

	}

}
