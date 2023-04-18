package com.watsoo.sfa.trial.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.watsoo.sfa.trial.dto.UserDataDto;

@Entity
@Table(name = "user_data")
public class UserData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "email")
	private String email;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "is_active")
	private Boolean isActive;

	public UserData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public UserData(Long id, String email, String name, String password, Boolean isActive) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.isActive = isActive;
	}

	public UserData(Long id) {
		this.id = id;
	}

	public UserDataDto convertToUserDataDto() {
		return new UserDataDto(this.getId(), this.getEmail(), this.getName(), null, this.getIsActive());
	}

}
