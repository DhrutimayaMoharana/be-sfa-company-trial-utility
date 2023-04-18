package com.watsoo.sfa.trial.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.watsoo.sfa.trial.model.UserData;

@JsonInclude(Include.NON_NULL)
public class UserDataPasswordChangeDto {

	private Long id;

	private String newPassword;

	private String oldPassword;

	public UserDataPasswordChangeDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public UserDataPasswordChangeDto(Long id, String newPassword, String oldPassword) {
		super();
		this.id = id;
		this.newPassword = newPassword;
		this.oldPassword = oldPassword;
	}

}
