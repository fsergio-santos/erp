package com.erp.crm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.erp.crm.model.Endereco;
import com.erp.crm.model.Pessoa;
import com.erp.crm.model.Telefone;
import com.erp.crm.service.EnderecoService;
import com.erp.crm.service.TelefoneService;

@Controller
@RequestMapping(value="/pessoa")
public class PessoaController {
	
	
	@Autowired
	private EnderecoService enderecoService;
	
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
