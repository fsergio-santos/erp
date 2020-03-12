package com.erp.security.web.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erp.config.page.PageWrapper;
import com.erp.config.util.ErpConfigure;
import com.erp.crm.model.PessoaFisica;
import com.erp.crm.repository.filtro.PessoaFisicaFiltro;
import com.erp.security.model.Escopo;
import com.erp.security.model.enumarate.Scopes;
import com.erp.security.service.EscopeService;


@Controller
@RequestMapping(value="/escopo")
public class EscopoController {

	@Autowired
	private EscopeService escopoService;
	

	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public ModelAndView listarEscopo(HttpServletRequest httpServletRequest, 
			                         @RequestParam(value="size", required=false) Optional<Integer> size,
			                         @RequestParam(value="page", required=false) Optional<Integer> page) {
	
		Pageable pageable = PageRequest.of(page.orElse(ErpConfigure.INITIAL_PAGE),
				                           size.orElse(ErpConfigure.INITIAL_PAGE_SIZE));
	
		PageWrapper<Escopo> pagina = new PageWrapper<>(escopoService.listEscopoPagination(pageable), 
				                                       size.orElse(ErpConfigure.INITIAL_PAGE_SIZE), 
				                                       httpServletRequest);
		
		ModelAndView mv = new ModelAndView("/security/escopo/escopo_lista");
		
		mv.addObject("pageSizes", ErpConfigure.PAGE_SIZES);
		mv.addObject("size", size.orElse(ErpConfigure.INITIAL_PAGE_SIZE));
		mv.addObject("pagina", pagina);
	
		return mv;
	}
	
	@RequestMapping(value="/novo_cadastro")
	public ModelAndView cadastrarNovoEscopo(Escopo escopo) {
		ModelAndView mv = new ModelAndView("/security/escopo/escopo");
		mv.addObject("escopo", escopo);
		return mv;
	}
	
	
	
	@RequestMapping(value="/salvar", method=RequestMethod.POST)
	public ModelAndView salvarCadastroEscopo(@Valid Escopo escopo, BindingResult result,  RedirectAttributes attr) {
		if (result.hasErrors()) {
			return cadastrarNovoEscopo(escopo);
		}
		escopoService.save(escopo);
		attr.addFlashAttribute("success", "Registro cadastrado com sucesso.");
		return cadastrarNovoEscopo(escopo);
	}
	
	@RequestMapping(value="/editar/{id}", method=RequestMethod.GET)
	public ModelAndView alterarCadastroEscopo(@PathVariable("id") Long id, ModelMap model) {
		ModelAndView mv = new ModelAndView("/security/escopo/escopo");
		Escopo escopo = escopoService.getOne(id);
		mv.addObject("escopo", escopo);
		return cadastrarNovoEscopo(escopo);
	}
	
	@RequestMapping(value="/editar", method=RequestMethod.POST)
	public ModelAndView alterarCadastroEscopo(@Valid Escopo escopo, BindingResult result,  RedirectAttributes attr) {
		if (result.hasErrors()) {
			return cadastrarNovoEscopo(escopo);
		}
		escopoService.save(escopo);
		attr.addFlashAttribute("success", "Registro cadastrado com sucesso.");
		return cadastrarNovoEscopo(escopo);
	}
	
	@RequestMapping(value="/excluir/{id}", method=RequestMethod.GET)
	public ModelAndView excluir(@PathVariable("id") Long id) {
		Escopo escopo = escopoService.getOne(id);
		ModelAndView mv = new ModelAndView("/security/escopo/excluir_escopo");
		mv.addObject("escopo", escopo);
		return mv;
	}
	
	@RequestMapping(value="/excluir", method=RequestMethod.POST)
	public String excluirEscopo(Escopo escopo, RedirectAttributes attr) {
		escopoService.removeById(escopo.getId());
		attr.addFlashAttribute("success", "Registro removido com sucesso.");
		return "redirect:/escopo/listar";
	}
	
	
	@RequestMapping(value="/consultar/{id}", method=RequestMethod.GET)
	public ModelAndView consulta(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("/security/escopo/consultar_escopo");
		Escopo escopo = escopoService.getOne(id);
		mv.addObject("escopo", escopo);
	    return mv;
	}
		

	@RequestMapping(value= {"/salvar","/editar","/excluir","/consultar"}, method=RequestMethod.POST, params="action=cancelar")
	public String cancelarCadastroEscopo() {
		return "redirect:/escopo/listar";
	}
	
	@ModelAttribute("scopes")
	public Scopes[] getScopes() {
		return Scopes.values();
	}
}
