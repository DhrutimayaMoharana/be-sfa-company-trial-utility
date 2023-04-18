package com.watsoo.sfa.trial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.watsoo.sfa.trial.dto.LoginRequestDto;
import com.watsoo.sfa.trial.dto.LoginResponseDto;
import com.watsoo.sfa.trial.dto.Response;
import com.watsoo.sfa.trial.service.TrialUserDetailsService;

@RestController
@RequestMapping("trial-user")
public class TrailUserDetailsController {

	@Autowired
	private TrialUserDetailsService trialUserDetailsService;

	@PostMapping("v1/login-trial-user")
	public ResponseEntity<?> userPermission(@RequestBody LoginRequestDto loginRequestDto) {
		LoginResponseDto respone = trialUserDetailsService.trialUserLogin(loginRequestDto);
		return new ResponseEntity<>(respone, HttpStatus.OK);
	}
	
	@PostMapping("v1/permission-trial-user/{id}")
	public ResponseEntity<?> userPermission(@PathVariable Long id) {
		Response<?> respone = trialUserDetailsService.userPermissionResposneById(id);
		return new ResponseEntity<>(respone, HttpStatus.valueOf(respone.getStatus()));
	}

}
