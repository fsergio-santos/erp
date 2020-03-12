package com.erp.security.web.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erp.config.page.PageWrapper;
import com.erp.config.util.ErpConfigure;
import com.erp.security.model.Role;
import com.erp.security.model.Usuario;
import com.erp.security.repository.filtro.RoleFiltro;
import com.erp.security.repository.filtro.UsuarioFiltro;
import com.erp.security.service.RoleService;
import com.erp.security.service.UsuarioService;
import com.erp.security.service.exception.EmailUsuarioCadastradoException;
import com.erp.security.service.exception.NomeUsuarioCadastradoException;



@Controller
@RequestMapping(value="/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
    @Autowired
	private RoleService roleService;
	
    @RequestMapping(value="/listar", method=RequestMethod.GET)
	public ModelAndView listarPermissao(UsuarioFiltro usuarioFiltro, HttpServletRequest httpServletRequest, 
			                         @RequestParam(value="size", required=false) Optional<Integer> size,
			                         @RequestParam(value="page", required=false) Optional<Integer> page) {
	
		Pageable pageable = PageRequest.of(page.orElse(ErpConfigure.INITIAL_PAGE),
				                           size.orElse(ErpConfigure.INITIAL_PAGE_SIZE));
	
		PageWrapper<Usuario> pagina = new PageWrapper<>(usuarioService.listUsuarioWithPagination(usuarioFiltro, pageable), 
				                                       size.orElse(ErpConfigure.INITIAL_PAGE_SIZE), 
				                                       httpServletRequest);
		
		ModelAndView mv = new ModelAndView("/security/usuario/usuario_lista");
		
		mv.addObject("pageSizes", ErpConfigure.PAGE_SIZES);
		mv.addObject("size", size.orElse(ErpConfigure.INITIAL_PAGE_SIZE));
		mv.addObject("pagina", pagina);
	
		return mv;
	}
	
	
	@RequestMapping(value="/novo_usuario")
	public ModelAndView cadastrarUsuario(Usuario usuario) {
		ModelAndView mv = new ModelAndView("/security/usuario/usuario");
		mv.addObject("usuario", usuario);
		return mv;
	}
	
	@RequestMapping(value="/salvar", method=RequestMethod.POST)
	public ModelAndView salvarCadastroUsuario(@Valid Usuario usuario, BindingResult result,  RedirectAttributes attr) {

		if (result.hasErrors()) {
			return cadastrarUsuario(usuario);
		}
		try {
			usuarioService.save(usuario);	
		} catch (EmailUsuarioCadastradoException e) {
			result.rejectValue("email", e.getMessage(), e.getMessage());
			return cadastrarUsuario(usuario);
		}catch (NomeUsuarioCadastradoException e) {
			result.rejectValue("username", e.getMessage(), e.getMessage());
			return cadastrarUsuario(usuario);
		}
		attr.addFlashAttribute("success", "Registro inserido com sucesso.");
		return cadastrarUsuario(usuario);
	}
	
	@RequestMapping(value="/editar/{id}", method=RequestMethod.GET)
	public ModelAndView editar(@PathVariable("id") Long id, ModelMap model) {
		Usuario usuario = usuarioService.getOne(id);
		return cadastrarUsuario(usuario);
	}

	@RequestMapping(value="/editar", method=RequestMethod.POST)
	public ModelAndView salvarEdicaoCadastroUsuario(@Valid Usuario usuario, BindingResult result,  RedirectAttributes attr) {

		if (result.hasErrors()) {
			return cadastrarUsuario(usuario);
		}
		usuarioService.update(usuario);	
		attr.addFlashAttribute("success", "Registro alterado com sucesso.");
		return cadastrarUsuario(usuario);
	}
	
	@RequestMapping(value="/excluir/{id}", method=RequestMethod.GET)
	public ModelAndView excluir(@PathVariable("id") Long id) {
		Usuario usuario = usuarioService.getOne(id);
		ModelAndView mv = new ModelAndView("/security/usuario/excluir_usuario");
		mv.addObject("usuario", usuario);
		return mv;
	}
	
	@RequestMapping(value="/excluir", method=RequestMethod.POST, params="action=excluirdadosusuario")
	public String excluirUsuario(Usuario usuario, RedirectAttributes attr) {
		usuarioService.removeById(usuario.getId());
		attr.addFlashAttribute("success", "Registro removido com sucesso.");
		return "redirect:/usuario/listar";
	}
	
	@RequestMapping(value="/consultar/{id}", method=RequestMethod.GET)
	public String consulta(@PathVariable("id") Long id,  ModelMap model) {
		Usuario usuario = usuarioService.getOne(id);
		model.addAttribute("usuario", usuario);
		return "/security/usuario/consultar_usuario";
	}
		

	@RequestMapping(value= {"/salvar","/editar","/excluir","/consultar"}, method=RequestMethod.POST, params="action=cancelar")
	public String cancelarCadastroUser() {
		return "redirect:/usuario/listar";
	}
	
	@ModelAttribute("roles")
	public List<Role> listaRoles(){
		return roleService.findAll();
	}
}
