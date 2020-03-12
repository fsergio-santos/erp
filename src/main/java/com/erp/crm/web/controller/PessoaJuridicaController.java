package com.erp.crm.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erp.config.page.PageWrapper;
import com.erp.config.util.ErpConfigure;
import com.erp.crm.model.Endereco;
import com.erp.crm.model.Pessoa;
import com.erp.crm.model.PessoaFisica;
import com.erp.crm.model.PessoaJuridica;
import com.erp.crm.model.Telefone;
import com.erp.crm.model.enumerate.EstadoCivil;
import com.erp.crm.model.enumerate.FormaPessoaJuridica;
import com.erp.crm.model.enumerate.NaturezaJuridica;
import com.erp.crm.model.enumerate.PorteEmpresa;
import com.erp.crm.model.enumerate.TipoPessoa;
import com.erp.crm.model.enumerate.TipoPessoaFisica;
import com.erp.crm.model.enumerate.TipoPessoaJuridica;
import com.erp.crm.repository.filtro.PessoaFiltro;
import com.erp.crm.repository.filtro.PessoaJuridicaFiltro;
import com.erp.crm.service.CidadeService;
import com.erp.crm.service.EnderecoService;
import com.erp.crm.service.EstadoService;
import com.erp.crm.service.LogradouroService;
import com.erp.crm.service.PaisService;
import com.erp.crm.service.PessoaJuridicaService;
import com.erp.crm.service.PessoaService;
import com.erp.crm.service.TelefoneService;
import com.erp.crm.service.exceptions.CnpjCpfExistente;
import com.erp.crm.service.sessao.PessoaTelefone;

@Controller
@RequestMapping(value="/pessoa_juridica")
public class PessoaJuridicaController {
	
	
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
	private PessoaJuridicaService pessoaJuridicaService;
	
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
	public ModelAndView listarPessoasJuridicas(PessoaJuridicaFiltro pessoaFiltro, 
			                                 HttpServletRequest httpServletRequest, 
			                                 @RequestParam(value="size", required=false) Optional<Integer> size,
			                                 @RequestParam(value="page", required=false) Optional<Integer> page) {
	
		Pageable pageable = PageRequest.of(page.orElse(ErpConfigure.INITIAL_PAGE),
				                           size.orElse(ErpConfigure.INITIAL_PAGE_SIZE));
	
		PageWrapper<PessoaJuridica> pagina = new PageWrapper<>(pessoaJuridicaService.listPessoaJuridicaPagination(pessoaFiltro, pageable), 
				                                             size.orElse(ErpConfigure.INITIAL_PAGE_SIZE), 
				                                             httpServletRequest);
		
		ModelAndView mv = new ModelAndView("/crm/pessoa_juridica/pessoa_lista");
		
		mv.addObject("pageSizes", ErpConfigure.PAGE_SIZES);
		mv.addObject("size", size.orElse(ErpConfigure.INITIAL_PAGE_SIZE));
		mv.addObject("pagina", pagina);
	
		return mv;
	}	
	
	
	@RequestMapping(value="/novo_cadastro", method=RequestMethod.GET)
	public ModelAndView cadastrarNovaPessoaJuridica(PessoaJuridica pessoa) {
		ModelAndView mv = new ModelAndView("/crm/pessoa_juridica/pessoa_juridica");
		mv.addObject("pessoa", pessoa);
		return mv;
	}
	
	
	@RequestMapping(value="/alterar_cadastro/{id}", method=RequestMethod.GET)
	public ModelAndView alterarCadastroPessoaJuridica(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("/crm/pessoa_juridica/pessoa_juridica");
		Optional<PessoaJuridica> pessoaJuridica = pessoaJuridicaService.findPessoaJuridicaPeloId(id);
		PessoaJuridica pessoa = new PessoaJuridica();
		if (pessoaJuridica.isPresent()) {
		   pessoa = pessoaJuridica.get(); 	
		   mv.addObject("pessoa", pessoa);
		} else {
           cadastrarNovaPessoaJuridica(pessoa);			
		}
		return mv;
	}
	
	
	@RequestMapping(value="/excluir_cadastro/{id}", method=RequestMethod.GET)
	public ModelAndView excluirCadastroPessoaJuridica(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		Optional<PessoaJuridica> pessoaJuridica = pessoaJuridicaService.findPessoaJuridicaPeloId(id);
		PessoaJuridica pessoa = new PessoaJuridica();
		if (pessoaJuridica.isPresent()) {
			pessoa = pessoaJuridica.get();
			mv.setViewName("/crm/pessoa_juridica/excluir_pessoa_juridica");
		} else {
			mv.setViewName("/crm/pessoa_juridica/pessoa_juridica");
		}
		mv.addObject("pessoa", pessoa);
		return mv;
	}
	
	
	@RequestMapping(value="/excluir", method=RequestMethod.POST,params="action=cadastrardadospessoais")
	public String excluirCadastrodePessoaJuridica(PessoaJuridica pessoa) {
		Optional<PessoaJuridica> pessoaJuridica = pessoaJuridicaService.findPessoaJuridicaPeloId(pessoa.getId());
        if (pessoaJuridica.isPresent()) {
        	pessoaJuridicaService.removeById(pessoaJuridica.get().getId());
        	pessoaService.removeById(pessoaJuridica.get().getId());
        }
		return "redirect:/pessoa_juridica/listar";
	}
	
	
	@RequestMapping(value="/consultar_cadastro/{id}", method=RequestMethod.GET)
	public ModelAndView consultarCadastroPessoaJuridica(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		Optional<PessoaJuridica> pessoaJuridica = pessoaJuridicaService.findPessoaJuridicaPeloId(id);
		PessoaJuridica pessoa = new PessoaJuridica();
		if (pessoaJuridica.isPresent()) {
			pessoa = pessoaJuridica.get();
			mv.setViewName("/crm/pessoa_juridica/consultar_pessoa_juridica");
		} else {
			mv.setViewName("/crm/pessoa_juridica/pessoa_juridica");
		}
		mv.addObject("pessoa", pessoa);
		return mv;
	}
	
	
	@RequestMapping(value="/endereco", method=RequestMethod.GET)
	public ModelAndView cadastrarEnderecoPessoaJuridica(PessoaJuridica pessoa) {
		ModelAndView mv = new ModelAndView("/crm/pessoa_juridica/pessoa_endereco");
		Optional<PessoaJuridica> pessoaJuridica = pessoaJuridicaService.findPessoaJuridicaPeloId(pessoa.getId());
		pessoa.setEnderecos(enderecoService.findEnderecoPeloIdPessoa(pessoa.getId()));
		mv.addObject("pessoa", pessoa);
		return mv;
	}
	
	
	@RequestMapping(value="/novo_endereco", method=RequestMethod.GET)
	public String cadastrarNovoEnderecoPessoaJuridica(PessoaJuridica pessoa, Model model) {
		model.addAttribute("pessoa", pessoa);
		return "crm/fragments/modalEndereco :: modal_endereco";
	}
	
	@RequestMapping(value="/contato", method=RequestMethod.GET)
	public ModelAndView cadastrarContatoPessoaJuridica(PessoaJuridica pessoa) {
		ModelAndView mv = new ModelAndView("/crm/pessoa_juridica/pessoa_contato");
		Optional<PessoaJuridica> pessoaJuridica = pessoaJuridicaService.findPessoaJuridicaPeloId(pessoa.getId());
		pessoa.setTelefones(telefoneService.findTelefonePeloIdPessoa(pessoa.getId()));
		mv.addObject("pessoa", pessoa);
		return mv;
	}
	
	@RequestMapping(value="/novo_contato", method=RequestMethod.GET)
	public String cadastrarNovoContatoPessoaJuridica(PessoaJuridica pessoa, Model model) {
		model.addAttribute("pessoa", pessoa);
		return "crm/fragments/editContato :: editar_contato";
	}
	
	
	@RequestMapping(value= {"/salvar","/editar"}, method=RequestMethod.POST, params="action=cadastrardadospessoais")
	public ModelAndView salvarDadosPessoaJuridica(@Valid PessoaJuridica pessoa, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			cadastrarNovaPessoaJuridica(pessoa);
		}
		try {
			pessoaJuridicaService.save(pessoa);
		} catch (CnpjCpfExistente e) {
			result.rejectValue("cnpj", e.getMessage(), e.getMessage());
			return cadastrarNovaPessoaJuridica(pessoa);
		}
		attr.addFlashAttribute("success", "Cadastro efetuado com sucesso!");
		return cadastrarNovaPessoaJuridica(pessoa);
	}
	
	
	/*
	 * gravando os dados do endereco da pessoa jurídica
	 */
	
	@RequestMapping(value= {"/salvar","/editar"}, method=RequestMethod.POST, params="action=cadastrardadosendereco")
	public String salvarDadosEnderecoPessoaJuridica(@Valid PessoaJuridica pessoa, BindingResult result, RedirectAttributes attr) {
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
	    pessoa = pessoaJuridicaService.save(pessoa);
	    attr.addFlashAttribute("success", "Cadastro efetuado com sucesso!");
	    return "redirect:/pessoa_juridica/enderecos/"+pessoa.getId();
	}
	
	@RequestMapping(value="/excluir", method=RequestMethod.POST, params="action=excluirdadosendereco")
	public ModelAndView excluirDadosEnderecoPessoaJuridica(PessoaJuridica pessoa, RedirectAttributes attr) {
		Endereco endereco = new Endereco();
		endereco.setId(pessoa.getEndereco().getId());
		enderecoService.removeById(endereco.getId());
		List<Endereco> listaEndereco = enderecoService.findEnderecoPeloIdPessoa(pessoa.getId());
		if (listaEndereco.size() != ErpConfigure.LISTA_VAZIA ) {
			pessoa.setEnderecos(listaEndereco);
            pessoa.setEnderecoCadastrado(ErpConfigure.ENDERECO_CADASTRADO);			
		}else {
			pessoa.setEnderecoCadastrado(ErpConfigure.ENDERECO_NAO_CADASTRADO);
			pessoa = pessoaJuridicaService.save(pessoa);
		}
		attr.addFlashAttribute("success", "Registro excluído com sucesso!");
        return cadastrarEnderecoPessoaJuridica(pessoa);
	}
	
	
	
	/*
	 * Consultas no sistema de endereços cadastrados para pessoa física 
	 */
	@RequestMapping(value="/enderecos/{id}", method=RequestMethod.GET)
	public ModelAndView buscarEnderecoPeloIdPessoaJuridica(@PathVariable("id") Long id, Model model) {
		Optional<PessoaJuridica> pessoaJuridica = pessoaJuridicaService.findPessoaJuridicaPeloId(id);
		PessoaJuridica pessoa = new PessoaJuridica();
        pessoa.setId(pessoaJuridica.get().getId());
        Endereco endereco = new Endereco();
		endereco.setPessoa(pessoa);
		pessoa.getEnderecos().add(endereco);
		return cadastrarEnderecoPessoaJuridica(pessoa);
	}
	
	
	/*
	 * 
	 * Gravando dados de contato da pessoa física. 
	 * 
	 */
	
	@RequestMapping(value= {"/salvar","/editar"}, method=RequestMethod.POST,params="action=cadastrardadoscontato")
	public String salvarDadosContatoPessoaJuridica(@Valid PessoaJuridica pessoa,RedirectAttributes attr) {
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
	    pessoa = pessoaJuridicaService.save(pessoa);
	    attr.addFlashAttribute("success", "Cadastro efetuado com sucesso!");
     	return "redirect:/pessoa_fisica/contatos/"+pessoa.getId();
	}
	
	@RequestMapping(value="/excluir", method=RequestMethod.POST, params="action=cadastrardadoscontato")
	public ModelAndView excluirContatoPessoaJuridica(PessoaJuridica pessoa, RedirectAttributes attr) {
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
        	pessoa = pessoaJuridicaService.save(pessoa);
        }
        attr.addFlashAttribute("success", "Registro excluído com sucesso!");
		return cadastrarContatoPessoaJuridica(pessoa);
	}
	
	 
	/*
	 * consultas no sistema de contatos de pessoa física
	 */
	@RequestMapping(value="/contatos/{id}", method=RequestMethod.GET)
	public ModelAndView buscarContatoPeloIdPessoaJuridica(@PathVariable("id") Long id) {
		Optional<PessoaJuridica> pessoaJuridica = pessoaJuridicaService.findPessoaJuridicaPeloId(id);
		PessoaJuridica pessoa = new PessoaJuridica();
		pessoa.setId(pessoaJuridica.get().getId());
		Telefone telefone = new Telefone();
		telefone.setPessoa(pessoa);
		pessoa.getTelefones().add(telefone);
		return cadastrarContatoPessoaJuridica(pessoa);
	}
	
	
	@RequestMapping(value= {"/salvar","/editar","/excluir","/consultar"}, method=RequestMethod.POST, params="action=cancelar")
	public String cancelarCadastroPessoa() {
		return "redirect:/pessoa_juridica/listar";
	}
	
	
	@ModelAttribute("naturezaJuridicas")
	public NaturezaJuridica[] listarNaturezaJuridica() {
		return NaturezaJuridica.values();
	}
	
	@ModelAttribute("porteEmpresas")
	public PorteEmpresa[] listarPorteEmpresas() {
		return PorteEmpresa.values();
	}
	
	@ModelAttribute("tipoPessoas")
	public TipoPessoaJuridica[] listarTipoPessoaJuridica() {
		return TipoPessoaJuridica.values();
	}
	
	@ModelAttribute("tipodePessoas")
	public TipoPessoa[] listarTipodePessoa() {
		return TipoPessoa.values();
	}
	
	
	@ModelAttribute("formaPessoaJuridicas")
	public FormaPessoaJuridica[] listarFormaPessoaJuridica() {
		return FormaPessoaJuridica.values();
	}

}
