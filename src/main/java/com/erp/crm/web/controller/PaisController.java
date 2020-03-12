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
import org.springframework.web.servlet.ModelAndView;

import com.erp.crm.model.Logradouro;
import com.erp.crm.model.Pais;
import com.erp.crm.model.Pessoa;
import com.erp.crm.service.PaisService;

@Controller
@RequestMapping(value="/pais")
public class PaisController {
	
	@Autowired
	private PaisService paisService;
	
	
	
	
	@RequestMapping(value="/novo_cadastro", method=RequestMethod.GET)
	public String cadastrarNovoPaisModal(Pais pais, Model model) {
		
		model.addAttribute("pais", pais);
		return "crm/pais/editPais :: editar_pais";

		
	}
	
	/*@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Pais>  buscarLogradouroCep(@PathVariable("id") Long id) {
		Pais pais = paisService.getOne(id);
		return pais == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(pais);
	}*/
	
	
	
	@RequestMapping(value= {"/salvar","/editar","/excluir","/consultar"}, method=RequestMethod.POST, params="action=cancelar")
	public String cancelarCadastroPais() {
		return "redirect:/pessoa_fisica/listar";
	}
	

}
