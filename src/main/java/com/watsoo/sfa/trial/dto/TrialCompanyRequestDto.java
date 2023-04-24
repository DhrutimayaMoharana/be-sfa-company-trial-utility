package com.watsoo.sfa.trial.dto;

import java.util.Date;

public class TrialCompanyRequestDto {

	private Long noOfUsers;

	private Long createdBy;

//	private String clientEmail;
//
//	private String clientName;
//
//	private Long usedBy;
//
//	private Date expiryDate;

	public TrialCompanyRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getNoOfUsers() {
		return noOfUsers;
	}

	public void setNoOfUsers(Long noOfUsers) {
		this.noOfUsers = noOfUsers;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

}
