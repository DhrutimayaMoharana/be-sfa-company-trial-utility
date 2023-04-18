package com.watsoo.sfa.trial.service;

import com.watsoo.sfa.trial.dto.LoginRequestDto;
import com.watsoo.sfa.trial.dto.LoginResponseDto;
import com.watsoo.sfa.trial.dto.Response;

public interface TrialUserDetailsService {

	LoginResponseDto trialUserLogin(LoginRequestDto loginRequestDto);

	Response<?> userPermissionResposneById(Long id);

}
