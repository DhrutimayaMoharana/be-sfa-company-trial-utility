package com.watsoo.sfa.trial.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LoginRequestDto {

	@NotNull(message = "The email address is required.")
	@NotEmpty(message = "The email address is required.")
	@Email(message = "The email address is invalid.")
	private String email;

	@NotNull(message = "The password is required.")
	@NotEmpty(message = "The password is required.")
	private String password;

	private String identifier;

	public LoginRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginRequestDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
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

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

}
