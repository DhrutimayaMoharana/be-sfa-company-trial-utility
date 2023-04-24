package com.watsoo.sfa.trial.service;

import com.watsoo.sfa.trial.dto.TrialCompanyDto;
import com.watsoo.sfa.trial.dto.TrialCompanyRequestDto;
import com.watsoo.sfa.trial.dto.Response;

public interface TrailCompanyService {

	Response<?> saveCompany(TrialCompanyDto companyDto);

	Response<?> getByIdCompany(Long id);

	Response<?> updateCompany(TrialCompanyDto companyDto);

	Response<?> getAllCompany(int pageNo, int pageSize, TrialCompanyDto companyDto);

	Response<?> addTrialCompanyV1(TrialCompanyRequestDto companyDto);

//	Response<?> addTrialCompanyV2(TrialCompanyRequestDto companyDto);

}
