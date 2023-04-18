package com.watsoo.sfa.trial.dto;

import java.util.Date;

public class TrialsUserDetailsDto {

	private Long id;

	private String name;

	private String adminEmail;

	private String[] userEmail;

	private Boolean isActive;

	private UserDataDto createdBy;

	private Date createdOn;

	private Long updatedBy;

	private Date updatedOn;

	public TrialsUserDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String[] getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String[] userEmail) {
		this.userEmail = userEmail;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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

	public TrialsUserDetailsDto(Long id, String name, String adminEmail, String[] userEmail, Boolean isActive,
			UserDataDto createdBy, Date createdOn, Long updatedBy, Date updatedOn) {
		super();
		this.id = id;
		this.name = name;
		this.adminEmail = adminEmail;
		this.userEmail = userEmail;
		this.isActive = isActive;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
	}

}
