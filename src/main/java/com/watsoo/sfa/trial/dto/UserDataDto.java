package com.watsoo.sfa.trial.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.watsoo.sfa.trial.model.UserData;

@JsonInclude(Include.NON_NULL)
public class UserDataDto {

	private Long id;

	private String email;

	private String name;

	private String password;

	private Boolean isActive;

	public UserDataDto() {
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

	public UserDataDto(Long id, String email, String name, String password, Boolean isActive) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.isActive = isActive;
	}

	public UserData convertToUserData() {
		return new UserData(this.getId(), this.getEmail(), this.getName(), this.getPassword(),
				this.getIsActive() != null ? this.getIsActive() : true);
	}

}
