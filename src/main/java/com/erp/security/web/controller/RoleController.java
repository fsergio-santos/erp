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
import com.erp.security.model.Role;
import com.erp.security.repository.filtro.PermissaoFiltro;
import com.erp.security.repository.filtro.RoleFiltro;
import com.erp.security.service.RoleService;
import com.erp.security.service.exception.NomeRoleCadastradaException;


@Controller
@RequestMapping(value="/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public ModelAndView listarPermissao(RoleFiltro roleFiltro, HttpServletRequest httpServletRequest, 
			                         @RequestParam(value="size", required=false) Optional<Integer> size,
			                         @RequestParam(value="page", required=false) Optional<Integer> page) {
	
		Pageable pageable = PageRequest.of(page.orElse(ErpConfigure.INITIAL_PAGE),
				                           size.orElse(ErpConfigure.INITIAL_PAGE_SIZE));
	
		PageWrapper<Role> pagina = new PageWrapper<>(roleService.listRoleWithPagination(roleFiltro, pageable), 
				                                       size.orElse(ErpConfigure.INITIAL_PAGE_SIZE), 
				                                       httpServletRequest);
		
		ModelAndView mv = new ModelAndView("/security/role/role_lista");
		
		mv.addObject("pageSizes", ErpConfigure.PAGE_SIZES);
		mv.addObject("size", size.orElse(ErpConfigure.INITIAL_PAGE_SIZE));
		mv.addObject("pagina", pagina);
	
		return mv;
	}
	
	@RequestMapping(value="/novo_cadastro")
	public ModelAndView cadastrarRole(Role role) {
		ModelAndView mv = new ModelAndView("/security/role/role");
		mv.addObject("role", role);
		return mv;
	}
	
	@RequestMapping(value= "/salvar", method=RequestMethod.POST)
	public ModelAndView salvarCadastroRole(@Valid Role role, BindingResult result,  RedirectAttributes attr) {
		if (result.hasErrors()) {
			return cadastrarRole(role);
		}
		try {
			role.setNome(role.getNome().toUpperCase());
			roleService.save(role);
		} catch (NomeRoleCadastradaException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return cadastrarRole(role);
		} 
		attr.addFlashAttribute("success", "Registro inserido com sucesso.");
		ModelAndView mv = new ModelAndView("redirect:/role/novo_cadastro");
		return mv;
	}
	
	@RequestMapping(value="/editar/{id}", method=RequestMethod.GET)
	public ModelAndView editar(@PathVariable("id") Long id) {
		Role role = roleService.getOne(id);
		return cadastrarRole(role);
	}
	
	
	@RequestMapping(value= "/editar", method=RequestMethod.POST)
	public ModelAndView salvarDadosRole(@Valid Role role, BindingResult result,  RedirectAttributes attr) {
		if (result.hasErrors()) {
			return cadastrarRole(role);
		}
		role.setNome(role.getNome().toUpperCase());
		roleService.update(role);
		attr.addFlashAttribute("success", "Registro alterado com sucesso.");
		ModelAndView mv = new ModelAndView("redirect:/role/novo_cadastro");
		return mv;
	}
	
	@RequestMapping(value="/excluir/{id}", method=RequestMethod.GET)
	public ModelAndView excluir(@PathVariable("id") Long id) {
		Role role = roleService.getOne(id);
		ModelAndView mv = new ModelAndView("/security/role/excluir_role");
		mv.addObject("role", role);
		return mv;
	}
	
	@RequestMapping(value="/excluir", method=RequestMethod.POST, params="action=excluirdadosrole")
	public String excluirRole(Role role, RedirectAttributes attr) {
		roleService.removeById(role.getId());
		attr.addFlashAttribute("success", "Registro removido com sucesso.");
		return "redirect:/role/listar";
	}
	
	@RequestMapping(value="/consultar/{id}", method=RequestMethod.GET)
	public String consulta(@PathVariable("id") Long id,  ModelMap model) {
		Role role = roleService.getOne(id);
		model.addAttribute("role", role);
		return "/security/role/consultar_role";
	}
		

	@RequestMapping(value= {"/salvar","/editar","/excluir","/consultar"}, method=RequestMethod.POST, params="action=cancelar")
	public String cancelarCadastroRole() {
		return "redirect:/role/listar";
	}
}
