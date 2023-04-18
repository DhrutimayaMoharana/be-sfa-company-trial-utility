package com.watsoo.sfa.trial.service;

import com.watsoo.sfa.trial.dto.LoginRequestDto;
import com.watsoo.sfa.trial.dto.LoginResponseDto;
import com.watsoo.sfa.trial.dto.Response;
import com.watsoo.sfa.trial.dto.UserDataDto;
import com.watsoo.sfa.trial.dto.UserDataPasswordChangeDto;

public interface UserDataService {

	Response<?> userLogin(LoginRequestDto loginRequestDto);

	Response<?> changePassword(UserDataPasswordChangeDto passwordChangeDto);

	Response<?> userCreate(UserDataDto userDataDto);

	LoginResponseDto userLoginV2(LoginRequestDto loginRequestDto);

	Response<?> userPermissionResposneById(Long id);

}
