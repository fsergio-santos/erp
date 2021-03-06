package com.erp.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.erp.security.service.EmailService;


@Service
public class EmailServiceImpl implements EmailService {

	
	@Autowired
	private JavaMailSender emailSender;
	

	@Override
	@Async
	public void sendSimpleMessage(String to, String subject, String text, String from) {
		try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            message.setFrom(from);
            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
		
	}

	@Override
	public void sendSimpleMessageUsingTemplate(String to, String subject, SimpleMailMessage template,
			String... templateArgs) {
		
		
	}

	@Override
	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {
		
		
	}


}
