package com.erp.crm.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.crm.model.Cidade;
import com.erp.crm.model.dto.ListaCidades;
import com.erp.crm.service.CidadeService;

@Controller
@RequestMapping(value="/cidade")
public class CidadeController {
	
	@Autowired
	private CidadeService cidadeService;
	
	@ResponseBody 
	@RequestMapping(value = "/buscarCidade", method=RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public List<ListaCidades> pesquisar(String nome) {
		validarTamanhoNome(nome);
		List<Cidade> listaCidade = cidadeService.findCidadeByNome(nome);
		List<ListaCidades> listaCidades = new ArrayList<>();
		for (Cidade cidade:listaCidade) {
			ListaCidades lst = new ListaCidades();
			lst.setId(cidade.getId());
			lst.setNome(cidade.getNome());
			lst.setEstado(cidade.getEstado().getNome());
			lst.setDdd(cidade.getDdd());
			lst.setSigla(cidade.getSigla());
			lst.setIbge(cidade.getIbge());
			lst.setIdestado(cidade.getEstado().getId());
			listaCidades.add(lst);
		}
		return listaCidades;
	}
	
	private void validarTamanhoNome(String nome) {
		if (StringUtils.isEmpty(nome) || nome.length() < 3) {
			throw new IllegalArgumentException();
		}
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();
	}
	
	

}
