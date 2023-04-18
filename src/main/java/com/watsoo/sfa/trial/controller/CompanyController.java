package com.watsoo.sfa.trial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.watsoo.sfa.trial.dto.TrialCompanyDto;
import com.watsoo.sfa.trial.dto.Response;
import com.watsoo.sfa.trial.service.TrailCompanyService;

@CrossOrigin("*")
@RestController
@RequestMapping("company")
public class CompanyController {

	@Autowired
	private TrailCompanyService companyService;

	@PostMapping("v1/companyAdd")
	public ResponseEntity<?> saveCompany(@RequestBody TrialCompanyDto companyDto) {
		Response<?> respone = companyService.saveCompany(companyDto);
		return new ResponseEntity<>(respone, HttpStatus.valueOf(respone.getStatus()));
	}

	@GetMapping("v1/companyView")
	public ResponseEntity<?> viewCompany(@RequestParam Long id) {
		Response<?> respone = companyService.getByIdCompany(id);
		return new ResponseEntity<>(respone, HttpStatus.valueOf(respone.getStatus()));
	}

	@PutMapping("v1/companyUpdate")
	public ResponseEntity<?> updateCompany(@RequestBody TrialCompanyDto companyDto) {
		Response<?> respone = companyService.updateCompany(companyDto);
		return new ResponseEntity<>(respone, HttpStatus.valueOf(respone.getStatus()));
	}

	@PostMapping("v1/companyAll")
	public ResponseEntity<?> getAllCompany(@RequestParam(required = false, defaultValue = "0") int pageNo,
			@RequestParam(required = false, defaultValue = "0") int pageSize,
			@RequestBody(required = false) TrialCompanyDto companyDto) {
		Response<?> respone = companyService.getAllCompany(pageNo, pageSize, companyDto);
		return new ResponseEntity<>(respone, HttpStatus.valueOf(respone.getStatus()));
	}

}
