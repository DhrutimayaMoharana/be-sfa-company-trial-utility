package com.watsoo.sfa.trial.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.watsoo.sfa.trial.dto.TrialCompanyDto;

@Entity
@Table(name = "company")
public class TrialCompany {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "company_identifier")
	private String companyIdentifier;

	@Column(name = "admin_email")
	private String adminEmail;

	@Column(name = "admin_password")
	private String adminPassword;

	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "user_password")
	private String userPassword;

	@ManyToOne
	@JoinColumn(name = "used_by", nullable = true)
	private UserData usedBy;

	@Column(name = "expiry_date")
	private Date expiryDate;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "token")
	private String token;

	@ManyToOne
	@JoinColumn(name = "created_by")
	private UserData createdBy;

	@CreationTimestamp
	@Column(name = "created_on")
	private Date createdOn;

	@Column(name = "updated_by")
	private Long updatedBy;

	@Column(name = "updated_on")
	private Date updatedOn;

	@OneToMany(mappedBy = "companyId", targetEntity = TrialUserDetails.class)
	private List<TrialUserDetails> trialUserDetails;

	public TrialCompany() {
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

	public UserData getUsedBy() {
		return usedBy;
	}

	public void setUsedBy(UserData usedBy) {
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

	public UserData getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserData createdBy) {
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

	public List<TrialUserDetails> getTrialUserDetails() {
		return trialUserDetails;
	}

	public void setTrialUserDetails(List<TrialUserDetails> trialUserDetails) {
		this.trialUserDetails = trialUserDetails;
	}

	public TrialCompany(Long id, String companyIdentifier, String adminEmail, String adminPassword, String userEmail,
			String userPassword, UserData usedBy, Date expiryDate, Boolean isActive, String token, UserData createdBy,
			Date createdOn, Long updatedBy, Date updatedOn) {
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
	}

	public TrialCompany(Long id) {
		this.id = id;
	}

	public TrialCompanyDto convertToCompanyDto() {
		return new TrialCompanyDto(this.getId(), this.getCompanyIdentifier(), this.getAdminEmail(), this.getAdminPassword(),
				this.getUserEmail(), this.getUserPassword(),
				this.getUsedBy() != null ? this.getUsedBy().convertToUserDataDto() : null, this.getExpiryDate(),
				this.getIsActive(), this.getToken(),
				this.getCreatedBy() != null ? this.getCreatedBy().convertToUserDataDto() : null, this.getCreatedOn(),
				this.getUpdatedBy(), this.getUpdatedOn(),
				this.getTrialUserDetails() != null
						? new TrialUserDetails().convertToTrailsUserDto(this.getTrialUserDetails())
						: null);
	}

}
