package com.watsoo.sfa.trial.dto;

import java.util.Date;

public class TransactionDto {

	private Long id;

	private TrialCompanyDto companyId;

	private UserDataDto assignedBy;

	private Date startDate;

	private Date endDate;

	private String companyName;

	private String clientEmail;

	private Boolean isActive;

	public TransactionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TrialCompanyDto getCompanyId() {
		return companyId;
	}

	public void setCompanyId(TrialCompanyDto companyId) {
		this.companyId = companyId;
	}

	public UserDataDto getAssignedBy() {
		return assignedBy;
	}

	public void setAssignedBy(UserDataDto assignedBy) {
		this.assignedBy = assignedBy;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public TransactionDto(Long id, TrialCompanyDto companyId, UserDataDto assignedBy, Date startDate, Date endDate,
			String companyName, String clientEmail, Boolean isActive) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.assignedBy = assignedBy;
		this.startDate = startDate;
		this.endDate = endDate;
		this.companyName = companyName;
		this.clientEmail = clientEmail;
		this.isActive = isActive;
	}

}
