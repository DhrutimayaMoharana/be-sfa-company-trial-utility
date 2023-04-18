package com.watsoo.sfa.trial.model;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.watsoo.sfa.trial.dto.TrialsUserDetailsDto;
import com.watsoo.sfa.trial.enums.UserType;

@Entity
@Table(name = "trial_user_details")
public class TrialUserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "user_type")
	private UserType userType;

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

	@ManyToOne
	@JoinColumn(name = "company_id")
	private TrialCompany companyId;

	public TrialUserDetails() {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
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

	public TrialCompany getCompanyId() {
		return companyId;
	}

	public void setCompanyId(TrialCompany companyId) {
		this.companyId = companyId;
	}

	public TrialUserDetails(Long id, String name, String email, String password, Boolean isActive, UserType userType,
			UserData createdBy, Date createdOn, Long updatedBy, Date updatedOn, TrialCompany companyId) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.isActive = isActive;
		this.userType = userType;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.companyId = companyId;
	}

	public TrialsUserDetailsDto convertToTrailsUserDto(List<TrialUserDetails> trialUserDetails) {
		List<String> userEmailList = trialUserDetails.stream().filter(e -> e.getUserType().equals(UserType.USER))
				.map(e -> e.getEmail()).collect(Collectors.toList());
		List<String> adminEmal = trialUserDetails.stream().filter(e -> e.getUserType().equals(UserType.ADMIN))
				.map(e -> e.getEmail()).collect(Collectors.toList());
		String[] userEmail = userEmailList.stream().toArray(String[]::new);
		return new TrialsUserDetailsDto(null, trialUserDetails.get(0).getName(), adminEmal.get(0), userEmail,
				trialUserDetails.get(0).getIsActive(), trialUserDetails.get(0).getCreatedBy().convertToUserDataDto(),
				trialUserDetails.get(0).getCreatedOn(), trialUserDetails.get(0).getUpdatedBy(),
				trialUserDetails.get(0).getUpdatedOn());
	}

}
