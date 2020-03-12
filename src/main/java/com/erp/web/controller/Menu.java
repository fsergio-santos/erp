package com.erp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Menu {

	@RequestMapping(value="/home")
	public String menu() {
		return "/menu/home";
	}
	
	@RequestMapping(value="/crm")
	public String crm() {
		return "/crm/crm";
	}
	
	@RequestMapping(value="/seguranca")
	public String seguranca() {
		return "/security/seguranca";
	}
	
}
