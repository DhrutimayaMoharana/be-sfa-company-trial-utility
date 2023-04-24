package com.watsoo.sfa.trial.dto;

import java.util.Date;

public class TrialCompanyRequestDto {

	private Long noOfUsers;

	private String clientEmail;

	private String clientName;

	private Long createdBy;

	private Long usedBy;

	private Date expiryDate;

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

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUsedBy() {
		return usedBy;
	}

	public void setUsedBy(Long usedBy) {
		this.usedBy = usedBy;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

}
