package com.watsoo.sfa.trial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.watsoo.sfa.trial.dto.LoginRequestDto;
import com.watsoo.sfa.trial.dto.LoginResponseDto;
import com.watsoo.sfa.trial.dto.Response;
import com.watsoo.sfa.trial.dto.UserDataDto;
import com.watsoo.sfa.trial.dto.UserDataPasswordChangeDto;
import com.watsoo.sfa.trial.service.UserDataService;

@RestController
@RequestMapping("user")
public class UserDataController {

	@Autowired
	private UserDataService userDataService;

	@PostMapping("v1/login")
	public ResponseEntity<?> userLogin(@RequestBody LoginRequestDto loginRequestDto) {
		Response<?> respone = userDataService.userLogin(loginRequestDto);
		return new ResponseEntity<>(respone, HttpStatus.valueOf(respone.getStatus()));
	}

	@PostMapping("v2/login")
	public ResponseEntity<?> userLoginV2(@RequestBody LoginRequestDto loginRequestDto) {
		LoginResponseDto respone = userDataService.userLoginV2(loginRequestDto);
		return new ResponseEntity<>(respone, HttpStatus.OK);
	}

	@PutMapping("v1/change/password")
	public ResponseEntity<?> changePassword(@RequestBody UserDataPasswordChangeDto passwordChangeDto) {
		Response<?> respone = userDataService.changePassword(passwordChangeDto);
		return new ResponseEntity<>(respone, HttpStatus.valueOf(respone.getStatus()));
	}

	@PostMapping("v1/createUser")
	public ResponseEntity<?> userCreate(@RequestBody UserDataDto userDataDto) {
		Response<?> respone = userDataService.userCreate(userDataDto);
		return new ResponseEntity<>(respone, HttpStatus.valueOf(respone.getStatus()));
	}

	@PostMapping("v1/permission-user/{id}")
	public ResponseEntity<?> userPermission(@PathVariable Long id) {
		Response<?> respone = userDataService.userPermissionResposneById(id);
		return new ResponseEntity<>(respone, HttpStatus.valueOf(respone.getStatus()));
	}

}
