package com.erp.crm.web.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.erp.config.rest.MensagensRest;
import com.erp.crm.model.PessoaFisica;
import com.erp.crm.service.PessoaFisicaService;

@RestController
@RequestMapping(value="/rest",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PessoaFisicaRestController {

	@Autowired
	private PessoaFisicaService pessoaFisicaService;
	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/pessoa_fisica/buscar_cpf_pessoa_fisica", method=RequestMethod.GET)
	public ResponseEntity<MensagensRest> buscarPessoaPeloCpf(@RequestParam("cpf") String cpf){
		Optional<PessoaFisica> pessoaFisica = pessoaFisicaService.findPessoaByCpf(cpf);
		MensagensRest msg = new MensagensRest();
		if (pessoaFisica.isPresent()) {
		    msg.setId("201");
		    msg.setMensagem("Cpf já foi cadastrado!");
		}
		return ResponseEntity.ok(msg);
	}
}
