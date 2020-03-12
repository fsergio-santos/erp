package com.erp.security.model.dto;

import com.erp.config.util.anotations.ValidarEmail;

public class Email {
	
	private String email;
	
	public Email() {
		super();
	}

	public Email(String email) {
		super();
		this.email = email;
	}

	@ValidarEmail
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
