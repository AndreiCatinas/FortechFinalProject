package com.fortech.service;

import java.util.Date;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	private Boolean enabled = true;

	private void send(MimeMessagePreparator mimePrep) {
		if (enabled) {
			mailSender.send(mimePrep);
		}
	}

	public void sendRecoveryEmail(String emailTo, String token) {
		MimeMessagePreparator mimePrep = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
				helper.setTo(emailTo);
				helper.setFrom(new InternetAddress("fortech.finalproject.ac@gmail.com"));
				helper.setSubject("Fortech App Password Reset");
				helper.setSentDate(new Date());
				String text = "Follow this link to reset your password: <a href = 'localhost:8080/reset/" + token + "'> Reset </a>";

				helper.setText(text);
			}
		};

		send(mimePrep);
	}

}
