package com.erp.security.web.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erp.config.page.PageWrapper;
import com.erp.config.util.ErpConfigure;
import com.erp.security.model.Permissao;
import com.erp.security.model.Permissao;
import com.erp.security.repository.filtro.PermissaoFiltro;
import com.erp.security.service.PermissaoService;
import com.erp.security.service.exception.NomePermissaoCadastradoException;


@Controller
@RequestMapping(value="/permissao")
public class PermissaoController {

	@Autowired
	private PermissaoService permissaoService;
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public ModelAndView listarPermissao(PermissaoFiltro permissaoFiltro, HttpServletRequest httpServletRequest, 
			                         @RequestParam(value="size", required=false) Optional<Integer> size,
			                         @RequestParam(value="page", required=false) Optional<Integer> page) {
	
		Pageable pageable = PageRequest.of(page.orElse(ErpConfigure.INITIAL_PAGE),
				                           size.orElse(ErpConfigure.INITIAL_PAGE_SIZE));
	
		PageWrapper<Permissao> pagina = new PageWrapper<>(permissaoService.listPermissaoPagination(permissaoFiltro, pageable), 
				                                       size.orElse(ErpConfigure.INITIAL_PAGE_SIZE), 
				                                       httpServletRequest);
		
		ModelAndView mv = new ModelAndView("/security/permissao/permissao_lista");
		
		mv.addObject("pageSizes", ErpConfigure.PAGE_SIZES);
		mv.addObject("size", size.orElse(ErpConfigure.INITIAL_PAGE_SIZE));
		mv.addObject("pagina", pagina);
	
		return mv;
	}
	
	@RequestMapping(value="/novo_cadastro")
	public ModelAndView cadastrarNovoPermissao(Permissao permissao) {
		ModelAndView mv = new ModelAndView("/security/permissao/permissao");
		mv.addObject("permissao", permissao);
		return mv;
	}
	
	@RequestMapping(value="/salvar", method=RequestMethod.POST)
	public ModelAndView salvarCadastroPermissao(@Valid Permissao permissao, BindingResult result,  RedirectAttributes attr) {
		if (result.hasErrors()) {
			return cadastrarNovoPermissao(permissao);
		}
		permissao.setNome(permissao.getNome().toUpperCase());
		try {
			permissaoService.save(permissao);
		} catch(NomePermissaoCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return cadastrarNovoPermissao(permissao);
		}
		attr.addFlashAttribute("success", "Registro cadastrado com sucesso.");
		return new ModelAndView("redirect:/permissao/novo_cadastro");
	}
	
	@RequestMapping(value="/editar/{id}", method=RequestMethod.GET)
	public ModelAndView alterarCadastroPermissao(@PathVariable("id") Long id, ModelMap model) {
		ModelAndView mv = new ModelAndView("/security/permissao/permissao");
		Permissao permissao = permissaoService.getOne(id);
		mv.addObject("permissao", permissao);
		return cadastrarNovoPermissao(permissao);
	}
	
	@RequestMapping(value="/editar", method=RequestMethod.POST)
	public ModelAndView editarCadastroPermissao(@Valid Permissao permissao, BindingResult result,  RedirectAttributes attr) {
		if (result.hasErrors()) {
			return cadastrarNovoPermissao(permissao);
		}
		permissao.setNome(permissao.getNome().toUpperCase());
		permissaoService.update(permissao);
		attr.addFlashAttribute("success", "Registro cadastrado com sucesso.");
		return new ModelAndView("redirect:/permissao/novo_cadastro");
	}
	
	@RequestMapping(value="/excluir/{id}", method=RequestMethod.GET)
	public ModelAndView excluir(@PathVariable("id") Long id) {
		Permissao permissao = permissaoService.getOne(id);
		ModelAndView mv = new ModelAndView("/security/permissao/excluir_permissao");
		mv.addObject("permissao", permissao);
		return mv;
	}
	
	@RequestMapping(value="/excluir", method=RequestMethod.POST)
	public String excluirPermissao(Permissao permissao, RedirectAttributes attr) {
		permissaoService.removeById(permissao.getId());
		attr.addFlashAttribute("success", "Registro removido com sucesso.");
		return "redirect:/permissao/listar";
	}
	
	
	@RequestMapping(value="/consultar/{id}", method=RequestMethod.GET)
	public ModelAndView consulta(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("/security/permissao/consultar_permissao");
		Permissao permissao = permissaoService.getOne(id);
		mv.addObject("permissao", permissao);
	    return mv;
	}
	
	@RequestMapping(value= {"/salvar","/editar","/excluir","/consultar"}, method=RequestMethod.POST, params="action=cancelar")
	public String cancelarCadastroPermissao() {
		return "redirect:/permissao/listar";
	}
}
