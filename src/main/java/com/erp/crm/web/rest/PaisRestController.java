package com.erp.crm.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.erp.config.rest.MensagensRest;
import com.erp.crm.model.Cidade;
import com.erp.crm.model.Pais;
import com.erp.crm.service.PaisService;

@RestController
@RequestMapping(value="/rest", consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PaisRestController {
	
	@Autowired
	private PaisService paisService;
	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/pais",method=RequestMethod.GET)
	public ResponseEntity<Pais>  buscarPaisBById(@RequestParam("id") Long id) {
		Pais pais = paisService.getOne(id);
		return pais == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(pais);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/pais/salvar", method=RequestMethod.POST)
	public ResponseEntity<Pais> salvarPaisModal(@RequestBody Pais pais){
		pais = paisService.save(pais);
		return ResponseEntity.ok(pais);
	}

}
