package com.erp.crm.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.erp.config.rest.MensagensRest;
import com.erp.crm.model.Cidade;
import com.erp.crm.model.dto.ListaCidades;
import com.erp.crm.service.CidadeService;

@RestController
@RequestMapping(value="/rest", consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CidadeRestController {
	
	@Autowired
	private CidadeService cidadeService;
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/cidade/buscar_nome_cidade", method=RequestMethod.GET)
	public ResponseEntity<MensagensRest> buscarCidadePeloNome(@RequestParam("id") Long id){
		Cidade cidade = cidadeService.getOne(id);
		MensagensRest msg = new MensagensRest();
		msg.setId(cidade.getId().toString());
		msg.setMensagem(cidade.getEstado().getNome());
		return ResponseEntity.ok(msg);
	}
	
	@ResponseBody 
	@RequestMapping(value = "/cidade/buscarCidade", method=RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
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
