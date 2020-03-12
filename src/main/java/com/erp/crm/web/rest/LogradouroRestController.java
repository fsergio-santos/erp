package com.erp.crm.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.erp.crm.model.Logradouro;
import com.erp.crm.service.LogradouroService;

@RestController
@RequestMapping(name="/rest", consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LogradouroRestController {
	
	@Autowired
	private LogradouroService logradouroService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/logradouro/cep/{cep}",method=RequestMethod.GET)
	public ResponseEntity<Logradouro>  buscarLogradouroCep(@RequestParam("cep") String cep) {
		Logradouro logradouro = logradouroService.retrieveLogradouroByCep(cep);
		return logradouro == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(logradouro);
	}
}
