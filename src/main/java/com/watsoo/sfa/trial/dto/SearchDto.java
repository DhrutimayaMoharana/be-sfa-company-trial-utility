package com.watsoo.sfa.trial.dto;

public class SearchDto {

	private Long companyId;

	private Long fromDate;

	private Long toDate;

	private Boolean status;

	private Object information;

	private Long userId;

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long id) {
		this.companyId = id;
	}

	public Long getFromDate() {
		return fromDate;
	}

	public void setFromDate(Long fromDate) {
		this.fromDate = fromDate;
	}

	public Long getToDate() {
		return toDate;
	}

	public void setToDate(Long toDate) {
		this.toDate = toDate;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Object getInformation() {
		return information;
	}

	public void setInformation(Object information) {
		this.information = information;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public SearchDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
