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

import com.erp.crm.model.Endereco;
import com.erp.crm.model.Pessoa;
import com.erp.crm.service.EnderecoService;

@RestController
@RequestMapping(value="/rest", consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class EnderecoRestController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/endereco/{id}", method=RequestMethod.GET)
	public ResponseEntity<Endereco>  buscarEnderecoPeloId(@PathVariable("id") Long id, Model model) {
		Endereco endereco = enderecoService.findEnderecoById(id);
		Pessoa pessoa = endereco.getPessoa();
		pessoa.setEndereco(endereco);
		model.addAttribute("pessoa", pessoa);
		return ResponseEntity.ok(endereco);
	}

}
