package com.watsoo.sfa.trial.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.watsoo.sfa.trial.dto.LoginRequestDto;
import com.watsoo.sfa.trial.dto.LoginResponseDto;
import com.watsoo.sfa.trial.dto.Response;
import com.watsoo.sfa.trial.dto.UserDataDto;
import com.watsoo.sfa.trial.dto.UserDataPasswordChangeDto;
import com.watsoo.sfa.trial.dto.UserPermissionDto;
import com.watsoo.sfa.trial.model.UserData;
import com.watsoo.sfa.trial.repository.TrailCompanyRepository;
import com.watsoo.sfa.trial.repository.UserDataRepository;
import com.watsoo.sfa.trial.service.UserDataService;

@Service
public class UserDataServiceImpl implements UserDataService {

	@Autowired
	private UserDataRepository userDataRepository;

	@Autowired
	private TrailCompanyRepository trailCompanyRepository;

	@Override
	public Response<?> userLogin(LoginRequestDto loginRequestDto) {
		try {
			if (loginRequestDto != null) {
				UserData userData = userDataRepository.getUserDataByEmail(loginRequestDto.getEmail());
				if (userData != null) {
					if (userData.getPassword().equals(loginRequestDto.getPassword())) {
						UserDataDto userDataDto = userData.convertToUserDataDto();
						return new Response<>(HttpStatus.OK.value(), "Succefully Logged in", userDataDto);
					} else {
						return new Response<>(HttpStatus.BAD_REQUEST.value(), "Invalid Credentials", null);
					}
				} else {
					return new Response<>(HttpStatus.BAD_REQUEST.value(), "Invalid Credentials", null);
				}
			} else {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Invalid Credentials", null);
			}
		} catch (Exception e) {
			return new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
		}
	}

	@Override
	public Response<?> changePassword(UserDataPasswordChangeDto userDataDto) {
		try {
			if (userDataDto != null && userDataDto.getId() != null && userDataDto.getOldPassword() != null
					&& userDataDto.getNewPassword() != null) {
				Optional<UserData> userData = userDataRepository.findById(userDataDto.getId());
				if (userData.isPresent()) {
					if (userData.get().getPassword().equals(userDataDto.getNewPassword())) {
						return new Response<>(HttpStatus.BAD_REQUEST.value(),
								"New password is matches with previous one", null);
					}
					if (!userData.get().getPassword().equals(userDataDto.getOldPassword())) {
						return new Response<>(HttpStatus.BAD_REQUEST.value(), "Old Password is Invalid", null);
					}
					UserData userUpdate = userData.get();
					userUpdate.setPassword(userDataDto.getNewPassword());
					userUpdate = userDataRepository.save(userUpdate);
					return new Response<>(HttpStatus.OK.value(), "Password Changed Succefully", userDataDto);
				} else {
					return new Response<>(HttpStatus.BAD_REQUEST.value(), "Userdata not found", null);
				}

			} else {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Invalid Input", null);
			}
		} catch (Exception e) {
			return new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
		}
	}

	@Override
	public Response<?> userCreate(UserDataDto userDataDto) {
		try {
			if (userDataDto != null) {
				UserData userData = userDataRepository.getUserDataByEmail(userDataDto.getEmail());
				if (userData != null) {
					return new Response<>(HttpStatus.ALREADY_REPORTED.value(), HttpStatus.ALREADY_REPORTED.name(),
							null);
				}
				userData = userDataDto.convertToUserData();
				userDataDto = userDataRepository.save(userData).convertToUserDataDto();
				return new Response<>(HttpStatus.CREATED.value(), "User Succefully created", userDataDto);
			} else {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Invalid Input", null);
			}
		} catch (Exception e) {
			return new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
		}
	}

	@Override
	public LoginResponseDto userLoginV2(LoginRequestDto loginRequestDto) {

		if (loginRequestDto != null) {
			UserData userData = userDataRepository.getUserDataByEmail(loginRequestDto.getEmail());
			if (userData != null) {
				if (userData.getPassword().equals(loginRequestDto.getPassword())) {
					UserDataDto userDataDto = userData.convertToUserDataDto();
					LoginResponseDto loginResponseDto = new LoginResponseDto(userData.getId(), null, userData.getName(),
							userData.getEmail(), null, null, null, null, null, null, null, null, null, null, null, null,
							null, null, null, null, userData, userDataDto);

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

		List<Long> findCompanyByUsedBy = trailCompanyRepository.findByUsedBy(id);

		UserPermissionDto userPermissionDto = new UserPermissionDto(findCompanyByUsedBy);

		return new Response<>(HttpStatus.OK.value(), "Success", userPermissionDto);

	}

}
