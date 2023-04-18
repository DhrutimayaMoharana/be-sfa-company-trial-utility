package com.watsoo.sfa.trial.dto;

import java.util.Date;

import com.watsoo.sfa.trial.model.TrialCompany;
import com.watsoo.sfa.trial.model.UserData;

public class TrialCompanyDto {

	private Long id;

	private String companyIdentifier;

	private String adminEmail;

	private String adminPassword;

	private String userEmail;

	private String userPassword;

	private UserDataDto usedBy;

	private Date expiryDate;

	private Boolean isActive;

	private String token;

	private UserDataDto createdBy;

	private Date createdOn;

	private Long updatedBy;

	private Date updatedOn;

	private String clientEmail;

	private String clientName;

	private TrialsUserDetailsDto trialsUserDetailsDto;

	public TrialCompanyDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyIdentifier() {
		return companyIdentifier;
	}

	public void setCompanyIdentifier(String companyIdentifier) {
		this.companyIdentifier = companyIdentifier;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public UserDataDto getUsedBy() {
		return usedBy;
	}

	public void setUsedBy(UserDataDto usedBy) {
		this.usedBy = usedBy;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserDataDto getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserDataDto createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
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

	public TrialsUserDetailsDto getTrialsUserDetailsDto() {
		return trialsUserDetailsDto;
	}

	public void setTrialsUserDetailsDto(TrialsUserDetailsDto trialsUserDetailsDto) {
		this.trialsUserDetailsDto = trialsUserDetailsDto;
	}

	public TrialCompanyDto(Long id, String companyIdentifier, String adminEmail, String adminPassword, String userEmail,
			String userPassword, UserDataDto usedBy, Date expiryDate, Boolean isActive, String token,
			UserDataDto createdBy, Date createdOn, Long updatedBy, Date updatedOn,
			TrialsUserDetailsDto trialsUserDetailsDto) {
		super();
		this.id = id;
		this.companyIdentifier = companyIdentifier;
		this.adminEmail = adminEmail;
		this.adminPassword = adminPassword;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.usedBy = usedBy;
		this.expiryDate = expiryDate;
		this.isActive = isActive;
		this.token = token;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.trialsUserDetailsDto = trialsUserDetailsDto;
	}

	public TrialCompany convertToCompany() {
		return new TrialCompany(this.getId(), this.getCompanyIdentifier(), this.getAdminEmail(), this.getAdminPassword(),
				this.getUserEmail(), this.getUserPassword(),
				this.getUsedBy() != null && this.getUsedBy().getId() != null ? new UserData(this.getUsedBy().getId())
						: null,
				this.getExpiryDate(), this.getIsActive() != null ? this.getIsActive() : true, this.getToken(),
				this.getCreatedBy() != null && this.getCreatedBy().getId() != null
						? new UserData(this.getCreatedBy().getId())
						: null,
				this.getCreatedOn(), this.getUpdatedBy(), this.getUpdatedOn());
	}

}
