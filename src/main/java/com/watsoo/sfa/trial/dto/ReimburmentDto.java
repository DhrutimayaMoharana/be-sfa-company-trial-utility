package com.watsoo.sfa.trial.dto;

public class ReimburmentDto {

	private Long id;
	private ReimursementTypeDto reimbursementType;
	private IdNameDto empType;
	private IdNameDto designation;
	private Double maxLimit;
	private Double rate;
	private Boolean isLinkWithOd;
	private Long siteId;
	private Long companyId;
	private Boolean isActive;
	private Long createdBy;
	private Long updatedBy;

	public ReimburmentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ReimursementTypeDto getReimbursementType() {
		return reimbursementType;
	}

	public void setReimbursementType(ReimursementTypeDto reimbursementType) {
		this.reimbursementType = reimbursementType;
	}

	public IdNameDto getEmpType() {
		return empType;
	}

	public void setEmpType(IdNameDto empType) {
		this.empType = empType;
	}

	public IdNameDto getDesignation() {
		return designation;
	}

	public void setDesignation(IdNameDto designation) {
		this.designation = designation;
	}

	public Double getMaxLimit() {
		return maxLimit;
	}

	public void setMaxLimit(Double maxLimit) {
		this.maxLimit = maxLimit;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Boolean getIsLinkWithOd() {
		return isLinkWithOd;
	}

	public void setIsLinkWithOd(Boolean isLinkWithOd) {
		this.isLinkWithOd = isLinkWithOd;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

}
