package com.watsoo.sfa.trial.dto;

import java.util.List;

public class TrialUserPermissionDto {

	Long companyId;

	List<Long> employeeList;

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

	public List<Long> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Long> employeeList) {
		this.employeeList = employeeList;
	}

	public TrialUserPermissionDto(Long companyId, List<Long> employeeList) {
		super();
		this.companyId = companyId;
		this.employeeList = employeeList;
	}

}
