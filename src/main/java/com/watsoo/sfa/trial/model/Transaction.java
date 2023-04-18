package com.watsoo.sfa.trial.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.watsoo.sfa.trial.dto.TransactionDto;

@Entity
@Table(name = "transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private TrialCompany companyId;

	@ManyToOne
	@JoinColumn(name = "assigned_by")
	private UserData assignedBy;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "client_email")
	private String clientEmail;

	@Column(name = "is_active")
	private Boolean isActive;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TrialCompany getCompanyId() {
		return companyId;
	}

	public void setCompanyId(TrialCompany companyId) {
		this.companyId = companyId;
	}

	public UserData getAssignedBy() {
		return assignedBy;
	}

	public void setAssignedBy(UserData assignedBy) {
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

	public Transaction(Long id, TrialCompany companyId, UserData assignedBy, Date startDate, Date endDate,
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

	public TransactionDto convertToTransactionDto() {
		return new TransactionDto(this.getId(), new TrialCompany().convertToCompanyDto(),
				new UserData().convertToUserDataDto(), this.getStartDate(), this.getEndDate(), this.getCompanyName(),
				this.getClientEmail(), this.getIsActive());
	}

}
