package com.watsoo.sfa.trial.dto;

public class TrialUserPermissionDto {

	Long companyId;

	public TrialUserPermissionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public TrialUserPermissionDto(Long companyId) {
		super();
		this.companyId = companyId;
	}

}
