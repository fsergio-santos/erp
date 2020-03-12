package com.erp.crm.web.controller;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erp.config.page.PageWrapper;
import com.erp.config.util.ErpConfigure;
import com.erp.crm.model.Cidade;
import com.erp.crm.model.Endereco;
import com.erp.crm.model.Estado;
import com.erp.crm.model.Logradouro;
import com.erp.crm.model.Pais;
import com.erp.crm.model.Pessoa;
import com.erp.crm.model.PessoaFisica;
import com.erp.crm.model.Telefone;
import com.erp.crm.model.enumerate.EstadoCivil;
import com.erp.crm.model.enumerate.ModeloTelefone;
import com.erp.crm.model.enumerate.RegimeCasamento;
import com.erp.crm.model.enumerate.Sexo;
import com.erp.crm.model.enumerate.TipoEndereco;
import com.erp.crm.model.enumerate.TipoPessoa;
import com.erp.crm.model.enumerate.TipoPessoaFisica;
import com.erp.crm.model.enumerate.TipoPessoaJuridica;
import com.erp.crm.model.enumerate.TipoTelefone;
import com.erp.crm.repository.filtro.PessoaFiltro;
import com.erp.crm.repository.filtro.PessoaFisicaFiltro;
import com.erp.crm.service.CidadeService;
import com.erp.crm.service.EnderecoService;
import com.erp.crm.service.EstadoService;
import com.erp.crm.service.LogradouroService;
import com.erp.crm.service.PaisService;
import com.erp.crm.service.PessoaFisicaService;
import com.erp.crm.service.PessoaService;
import com.erp.crm.service.TelefoneService;
import com.erp.crm.service.exceptions.CnpjCpfExistente;
import com.erp.crm.service.sessao.PessoaTelefone;


/**
 * @author Administrador
 *
 */
@Controller
@RequestMapping(value= {"/pessoa_fisica"})
public class PessoaFisicaController {
	
	@Autowired
	private PaisService paisService;
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private LogradouroService logradouroService;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PessoaFisicaService pessoaFisicaService;
	
	@Autowired
	private PessoaTelefone pessoaTelefone;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private TelefoneService telefoneService;
	
	
	@InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public ModelAndView listarPessoasFisicas(PessoaFisicaFiltro pessoaFiltro, 
			                                 HttpServletRequest httpServletRequest, 
			                                 @RequestParam(value="size", required=false) Optional<Integer> size,
			                                 @RequestParam(value="page", required=false) Optional<Integer> page) {
	
		Pageable pageable = PageRequest.of(page.orElse(ErpConfigure.INITIAL_PAGE),
				                           size.orElse(ErpConfigure.INITIAL_PAGE_SIZE));
	
		PageWrapper<PessoaFisica> pagina = new PageWrapper<>(pessoaFisicaService.listPessoaFisicaPagination(pessoaFiltro, pageable), 
				                                             size.orElse(ErpConfigure.INITIAL_PAGE_SIZE), 
				                                             httpServletRequest);
		
		ModelAndView mv = new ModelAndView("/crm/pessoa_fisica/pessoa_lista");
		
		mv.addObject("pageSizes", ErpConfigure.PAGE_SIZES);
		mv.addObject("size", size.orElse(ErpConfigure.INITIAL_PAGE_SIZE));
		mv.addObject("pagina", pagina);
	
		return mv;
	}

	@RequestMapping(value="/novo_cadastro", method=RequestMethod.GET)
	public ModelAndView cadastrarNovaPessoaFisica(PessoaFisica pessoa) {
		ModelAndView mv = new ModelAndView("/crm/pessoa_fisica/pessoa_fisica");
		mv.addObject("pessoa", pessoa);
		return mv;
	}
	
	@RequestMapping(value="/alterar_cadastro/{id}", method=RequestMethod.GET)
	public ModelAndView alterarCadastroPessoaFisica(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("/crm/pessoa_fisica/pessoa_fisica");
		Optional<PessoaFisica> pessoaFisica = pessoaFisicaService.findPessoaFisicaPeloId(id);
		PessoaFisica pessoa = new PessoaFisica();
		if (pessoaFisica.isPresent()) {
		   pessoa = pessoaFisica.get(); 	
		   mv.addObject("pessoa", pessoa);
		} else {
           cadastrarNovaPessoaFisica(pessoa);			
		}
		return mv;
	}
	
	@RequestMapping(value="/excluir_cadastro/{id}", method=RequestMethod.GET)
	public ModelAndView excluirCadastroPessoaFisica(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		Optional<PessoaFisica> pessoaFisica = pessoaFisicaService.findPessoaFisicaPeloId(id);
		PessoaFisica pessoa = new PessoaFisica();
		if (pessoaFisica.isPresent()) {
			pessoa = pessoaFisica.get();
			mv.setViewName("/crm/pessoa_fisica/excluir_pessoa_fisica");
		} else {
			mv.setViewName("/crm/pessoa_fisica/pessoa_fisica");
		}
		mv.addObject("pessoa", pessoa);
		return mv;
	}
	
	@RequestMapping(value="/excluir", method=RequestMethod.POST,params="action=cadastrardadospessoais")
	public String excluirCadastrodePessoaFisica(PessoaFisica pessoa) {
		Optional<PessoaFisica> pessoaFisica = pessoaFisicaService.findPessoaFisicaPeloId(pessoa.getId());
        if (pessoaFisica.isPresent()) {
        	pessoaFisicaService.removeById(pessoaFisica.get().getId());
        	pessoaService.removeById(pessoaFisica.get().getId());
        }
		return "redirect:/pessoa_fisica/listar";
	}
	
	@RequestMapping(value="/consultar_cadastro/{id}", method=RequestMethod.GET)
	public ModelAndView consultarCadastroPessoaFisica(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		Optional<PessoaFisica> pessoaFisica = pessoaFisicaService.findPessoaFisicaPeloId(id);
		PessoaFisica pessoa = new PessoaFisica();
		if (pessoaFisica.isPresent()) {
			pessoa = pessoaFisica.get();
			mv.setViewName("/crm/pessoa_fisica/consultar_pessoa_fisica");
		} else {
			mv.setViewName("/crm/pessoa_fisica/pessoa_fisica");
		}
		mv.addObject("pessoa", pessoa);
		return mv;
	}
	
	@RequestMapping(value="/endereco", method=RequestMethod.GET)
	public ModelAndView cadastrarEnderecoPessoaFisica(PessoaFisica pessoa) {
		ModelAndView mv = new ModelAndView("/crm/pessoa_fisica/pessoa_endereco");
		Optional<PessoaFisica> pessoaFisica = pessoaFisicaService.findPessoaFisicaPeloId(pessoa.getId());
		pessoa.setEnderecos(enderecoService.findEnderecoPeloIdPessoa(pessoa.getId()));
		mv.addObject("pessoa", pessoa);
		return mv;
	}
	
	@RequestMapping(value="/novo_endereco", method=RequestMethod.GET)
	public String cadastrarNovoEnderecoPessoaFisica(PessoaFisica pessoa, Model model) {
		model.addAttribute("pessoa", pessoa);
		return "crm/fragments/modalEndereco :: modal_endereco";
	}
	
	@RequestMapping(value="/contato", method=RequestMethod.GET)
	public ModelAndView cadastrarContatoPessoaFisica(PessoaFisica pessoa) {
		ModelAndView mv = new ModelAndView("/crm/pessoa_fisica/pessoa_contato");
		Optional<PessoaFisica> pessoaFisica = pessoaFisicaService.findPessoaFisicaPeloId(pessoa.getId());
		pessoa.setTelefones(telefoneService.findTelefonePeloIdPessoa(pessoa.getId()));
		mv.addObject("pessoa", pessoa);
		return mv;
	}
	
	@RequestMapping(value="/novo_contato", method=RequestMethod.GET)
	public String cadastrarNovoContatoPessoaFisica(PessoaFisica pessoa, Model model) {
		model.addAttribute("pessoa", pessoa);
		return "crm/fragments/editContato :: editar_contato";
	}
	
	
	/**
	 * gravar registro - dados da pessoa fisica
	 * 
	 */
	
	@RequestMapping(value= {"/salvar","/editar"}, method=RequestMethod.POST, params="action=cadastrardadospessoais")
	public ModelAndView salvarDadosPessoaFisica(@Valid PessoaFisica pessoa, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			cadastrarNovaPessoaFisica(pessoa);
		}
		try {
			pessoaFisicaService.save(pessoa);
		} catch (CnpjCpfExistente e) {
			result.rejectValue("cpf", e.getMessage(), e.getMessage());
			return cadastrarNovaPessoaFisica(pessoa);
		}
		attr.addFlashAttribute("success", "Cadastro efetuado com sucesso!");
		return cadastrarNovaPessoaFisica(pessoa);
	}
	
	
	/*
	 * gravando os dados do endereco da pessoa física
	 */
	
	@RequestMapping(value= {"/salvar","/editar"}, method=RequestMethod.POST, params="action=cadastrardadosendereco")
	public String salvarDadosEnderecoPessoaFisica(@Valid PessoaFisica pessoa, BindingResult result, RedirectAttributes attr) {
		Endereco endereco = new Endereco();
		if (pessoa.getEndereco().getId()!=null) {
			endereco.setId(pessoa.getEndereco().getId());
		}
		endereco.setCidade(pessoa.getEndereco().getCidade());
		endereco.setLogradouro(pessoa.getEndereco().getLogradouro());
		endereco.setNumero(pessoa.getEndereco().getNumero());
		endereco.setTipoEndereco(pessoa.getEndereco().getTipoEndereco());
        endereco.setPessoa(pessoa);
   	    enderecoService.save(endereco);
	    pessoa.setEndereco(new Endereco());
	    pessoa.getEnderecos().clear();
	    List<Endereco> listaEnderecoCadastrado = enderecoService.findEnderecoPeloIdPessoa(pessoa.getId());
	    pessoa.setEnderecos(listaEnderecoCadastrado);
	    pessoa.setEnderecoCadastrado(ErpConfigure.ENDERECO_CADASTRADO);
	    pessoa = pessoaFisicaService.save(pessoa);
	    attr.addFlashAttribute("success", "Cadastro efetuado com sucesso!");
	    return "redirect:/pessoa_fisica/enderecos/"+pessoa.getId();
	}
	
	@RequestMapping(value="/excluir", method=RequestMethod.POST, params="action=excluirdadosendereco")
	public ModelAndView excluirDadosEnderecoPessoaFisica(PessoaFisica pessoa, RedirectAttributes attr) {
		Endereco endereco = new Endereco();
		endereco.setId(pessoa.getEndereco().getId());
		enderecoService.removeById(endereco.getId());
		List<Endereco> listaEndereco = enderecoService.findEnderecoPeloIdPessoa(pessoa.getId());
		if (listaEndereco.size() != ErpConfigure.LISTA_VAZIA ) {
			pessoa.setEnderecos(listaEndereco);
            pessoa.setEnderecoCadastrado(ErpConfigure.ENDERECO_CADASTRADO);			
		}else {
			pessoa.setEnderecoCadastrado(ErpConfigure.ENDERECO_NAO_CADASTRADO);
			pessoa = pessoaFisicaService.save(pessoa);
		}
		attr.addFlashAttribute("success", "Registro excluído com sucesso!");
        return cadastrarEnderecoPessoaFisica(pessoa);
	}
	
	
	
	/*
	 * Consultas no sistema de endereços cadastrados para pessoa física 
	 */
	@RequestMapping(value="/enderecos/{id}", method=RequestMethod.GET)
	public ModelAndView buscarEnderecoPeloIdPessoaFisica(@PathVariable("id") Long id, Model model) {
		Optional<PessoaFisica> pessoaFisica = pessoaFisicaService.findPessoaFisicaPeloId(id);
		PessoaFisica pessoa = new PessoaFisica();
        pessoa.setId(pessoaFisica.get().getId());
        Endereco endereco = new Endereco();
		endereco.setPessoa(pessoa);
		pessoa.getEnderecos().add(endereco);
		return cadastrarEnderecoPessoaFisica(pessoa);
	}
	
	
	
	/*
	 * 
	 * Gravando dados de contato da pessoa física. 
	 * 
	 */
	
	@RequestMapping(value= {"/salvar","/editar"}, method=RequestMethod.POST,params="action=cadastrardadoscontato")
	public String salvarDadosContatoPessoaFisica(@Valid PessoaFisica pessoa,RedirectAttributes attr) {
		Telefone telefone = new Telefone();
		if (pessoa.getTelefone().getId()!=null) {
			telefone.setId(pessoa.getTelefone().getId());
		}
		telefone.setModeloTelefone(pessoa.getTelefone().getModeloTelefone());
		telefone.setTipoTelefone(pessoa.getTelefone().getTipoTelefone());
		telefone.setNumeroTelefone(pessoa.getTelefone().getNumeroTelefone());
		telefone.setPessoa(pessoa);
	    telefone = telefoneService.save(telefone);
        pessoa.setTelefone(new Telefone());	
        pessoa.getTelefones().clear();
	    pessoa.setTelefoneCadastrado(ErpConfigure.TELEFONE_CADASTRADO);
	    pessoa = pessoaFisicaService.save(pessoa);
	    attr.addFlashAttribute("success", "Cadastro efetuado com sucesso!");
     	return "redirect:/pessoa_fisica/contatos/"+pessoa.getId();
	}
	
	@RequestMapping(value="/excluir", method=RequestMethod.POST, params="action=cadastrardadoscontato")
	public ModelAndView excluirContatoPessoaFisica(PessoaFisica pessoa, RedirectAttributes attr) {
 		Telefone telefone = telefoneService.getOne(pessoa.getTelefone().getId());
        pessoa.setId(telefone.getPessoa().getId());
        pessoa.setId(telefone.getPessoa().getId());
        pessoa.setNome(telefone.getPessoa().getNome());
        pessoa.setTelefone(new Telefone());
        telefoneService.removeById(telefone.getId());
        List<Telefone> listaTelefone = telefoneService.findTelefonePeloIdPessoa(pessoa.getId());
        if (listaTelefone.size() != ErpConfigure.LISTA_VAZIA ) {
        	pessoa.setTelefones(listaTelefone);
        } else {
        	pessoa.setTelefoneCadastrado(ErpConfigure.TELEFONE_NAO_CADASTRADO);
        	pessoa = pessoaFisicaService.save(pessoa);
        }
        attr.addFlashAttribute("success", "Registro excluído com sucesso!");
		return cadastrarContatoPessoaFisica(pessoa);
	}
	
	 
	/*
	 * consultas no sistema de contatos de pessoa física
	 */
	@RequestMapping(value="/contatos/{id}", method=RequestMethod.GET)
	public ModelAndView buscarContatoPeloIdPessoaFisica(@PathVariable("id") Long id) {
		Optional<PessoaFisica> pessoaFisica = pessoaFisicaService.findPessoaFisicaPeloId(id);
		PessoaFisica pessoa = new PessoaFisica();
		pessoa.setId(pessoaFisica.get().getId());
		Telefone telefone = new Telefone();
		telefone.setPessoa(pessoa);
		pessoa.getTelefones().add(telefone);
		return cadastrarContatoPessoaFisica(pessoa);
	}
	
	
	@RequestMapping(value= {"/salvar","/editar","/excluir","/consultar"}, method=RequestMethod.POST, params="action=cancelar")
	public String cancelarCadastroPessoa() {
		return "redirect:/pessoa_fisica/listar";
	}
	

	
	/*
	 * Carregando dados estáticos.
	 */
	
	@ModelAttribute("paises")
	public List<Pais> findAll(){
		return paisService.findAll();
	}
	
	
	@ModelAttribute("estadocivis")
	public EstadoCivil[] listarEstadoCivil() {
		return EstadoCivil.values();
	}
	
	@ModelAttribute("sexos")
	public Sexo[] listarSexo() {
		return Sexo.values();
	}
	
	@ModelAttribute("estados")
	public List<Estado> listaEstados(){
		return estadoService.findAll();
	}
	
	@ModelAttribute("regimecasamentos")
	public RegimeCasamento[] listarRegimeCasamento(){
		return RegimeCasamento.values();
	}
	
	@ModelAttribute("tipos")
	public TipoEndereco[] listaTipoEndereco() {
		return TipoEndereco.values();
	}
	
	@ModelAttribute("tiposTelefone")
	public TipoTelefone[] getTipoTelefone() {
		return TipoTelefone.values();
	}
	
	@ModelAttribute("modelosTelefone")
	public ModeloTelefone[] getModeloTelefone() {
		return ModeloTelefone.values();
	}
	
	@ModelAttribute("tipoPessoas")
	public TipoPessoaFisica[] listarTipoPessoaFisica() {
		return TipoPessoaFisica.values();
	}
	
	@ModelAttribute("tipodePessoas")
	public TipoPessoa[] listarTipodePessoa() {
		return TipoPessoa.values();
	}
	
}


/*@RequestMapping(value="/endereco_pessoa/{id}", method=RequestMethod.GET)
public ModelAndView buscarEnderecoPeloIdEndereco(@PathVariable("id") Long id) {
	Endereco endereco = enderecoService.getOne(id);
	Pessoa pessoa = new Pessoa();
	pessoa.setId(endereco.getPessoa().getId());
	pessoa.setNome(endereco.getPessoa().getNome());
	pessoa.setEndereco(new Endereco());
	pessoa.getEndereco().setId(endereco.getId());
	pessoa.getEndereco().setCidade(endereco.getCidade());
	pessoa.getEndereco().setLogradouro(endereco.getLogradouro());
	pessoa.getEndereco().setNumero(endereco.getNumero());
	pessoa.getEndereco().setTipoEndereco(endereco.getTipoEndereco());
	List<Endereco> listaEndereco = enderecoService.findEnderecoPeloIdPessoa(pessoa.getId());
	pessoa.setEnderecos(listaEndereco);
	return cadastrarEnderecoPessoaFisica(pessoa);
}*/

/*
 * Consulta de contatos cadastrados para pessoa física
 */

/*@RequestMapping(value="/contato_pessoa/{id}", method=RequestMethod.GET)
public ModelAndView buscarContatoPeloIdTelefone(@PathVariable("id") Long id) {
	Telefone telefone = telefoneService.getOne(id);
	Pessoa pessoa = new Pessoa();
	pessoa.setId(telefone.getPessoa().getId());
	pessoa.setNome(telefone.getPessoa().getNome());
	pessoa.setTelefone(new Telefone());
	pessoa.getTelefone().setId(telefone.getId());
	pessoa.getTelefone().setTipoTelefone(telefone.getTipoTelefone());
	pessoa.getTelefone().setModeloTelefone(telefone.getModeloTelefone());
	pessoa.getTelefone().setNumeroTelefone(telefone.getNumeroTelefone());
	List<Telefone> listaTelefone = telefoneService.findTelefonePeloIdPessoa(pessoa.getId());
	pessoa.setTelefones(listaTelefone);
	return cadastrarContatoPessoaFisica(pessoa);
}*/
