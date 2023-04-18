package com.watsoo.sfa.trial.serviceImpl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.watsoo.sfa.trial.dto.SendEmailRequest;
import com.watsoo.sfa.trial.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender sender;

	@Override
	public void sendMail(SendEmailRequest obj) {
		try {

//			Properties prop = new Properties();
//			prop.put("mail.smtp.auth", true);
//			prop.put("mail.smtp.starttls.enable", true);
//
//			prop.put("mail.smtp.EnableSSL.enable", "true");
//			prop.put("mail.smtp.host", "smtp.gmail.com");
//			prop.put("mail.smtp.port", "587");
//			prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

//			Session session = Session.getInstance(prop, new Authenticator() {
//				@Override
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication(obj.getSenderEmail(), obj.getSenderPassword());
//				}
//			});

			List<String> getToRecipients = addToAddresses(Arrays.asList(obj.getToEmailIds()));

			MimeMessage mimeMsg = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, true);

			helper.setTo(obj.getToEmailIds());
			helper.setSubject(obj.getSubject());
			helper.setText(obj.getContent(), true);
			if (obj.getCcEmailIds()!=null && obj.getCcEmailIds().length > 0) {
				helper.setCc(obj.getCcEmailIds());
			}
			EmailThread sendEmail = new EmailThread(sender, mimeMsg);
			Thread parallelThread = new Thread(sendEmail);
			parallelThread.setPriority(Thread.MAX_PRIORITY);
			parallelThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<String> addToAddresses(List<String> to) {
		List<String> invalidAddresses = new LinkedList<>();
		List<String> validAddresses = new LinkedList<>();
		to.stream().forEachOrdered(email -> {
			if (validateEmail(email.trim()))
				validAddresses.add(email.trim());
			else
				invalidAddresses.add(email.trim());
		});

		return Arrays.asList(String.join(",", validAddresses.stream().toArray(String[]::new)),
				String.join(",", invalidAddresses.stream().toArray(String[]::new)));
	}

	private Boolean validateEmail(String email) {
		return EmailValidator.getInstance().isValid(email);
	}

}
