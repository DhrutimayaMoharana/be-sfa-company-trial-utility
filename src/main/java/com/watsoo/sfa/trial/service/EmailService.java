package com.watsoo.sfa.trial.service;

import com.watsoo.sfa.trial.dto.SendEmailRequest;

public interface EmailService {

//	void sendMail(String subject, String email, String defaultPass, String name);

	void sendMail(SendEmailRequest obj);

}
