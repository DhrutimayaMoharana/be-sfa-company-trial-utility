package com.watsoo.sfa.trial.dto;

import java.util.List;

public class UserPermissionDto {

	List<Long> companyId;

	public UserPermissionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Long> getCompanyId() {
		return companyId;
	}

	public void setCompanyId(List<Long> companyId) {
		this.companyId = companyId;
	}

	public UserPermissionDto(List<Long> companyId) {
		super();
		this.companyId = companyId;
	}

}
