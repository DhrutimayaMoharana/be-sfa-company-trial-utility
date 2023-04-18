package com.watsoo.sfa.trial.dto;

import java.util.Date;

public class CompanyDto {
	private Long id;
	private String name;
	private String shortName;
	private String headerLogo;
	private String printLogo;
	private String details;
	private Boolean isActive;
	private Date createdOn;
	private Long createdBy;

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

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getHeaderLogo() {
		return headerLogo;
	}

	public void setHeaderLogo(String headerLogo) {
		this.headerLogo = headerLogo;
	}

	public String getPrintLogo() {
		return printLogo;
	}

	public void setPrintLogo(String printLogo) {
		this.printLogo = printLogo;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public CompanyDto(Long id, String name, String shortName, String headerLogo, String printLogo, String details,
			Boolean isActive, Date createdOn, Long createdBy) {
		super();
		this.id = id;
		this.name = name;
		this.shortName = shortName;
		this.headerLogo = headerLogo;
		this.printLogo = printLogo;
		this.details = details;
		this.isActive = isActive;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
	}

}
