package com.erp.crm.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.erp.crm.model.Pessoa;
import com.erp.crm.model.Telefone;
import com.erp.crm.service.TelefoneService;

@RestController
@RequestMapping(value="/rest", consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TelefoneRestController {
	
	
	@Autowired
	private TelefoneService telefoneService;
	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/contato/{id}", method=RequestMethod.GET)
	public ResponseEntity<Telefone>  buscarContatoPeloId(@PathVariable("id") Long id, Model model) {
		Telefone telefone = telefoneService.findTelefoneById(id);
		Pessoa pessoa = telefone.getPessoa();
		pessoa.setTelefone(telefone);
		model.addAttribute("pessoa", pessoa);
		return ResponseEntity.ok(telefone);
	}

}
