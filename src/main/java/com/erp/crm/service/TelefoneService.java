package com.erp.crm.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erp.config.util.service.GenericService;
import com.erp.crm.model.Endereco;
import com.erp.crm.model.PessoaJuridica;
import com.erp.crm.model.Telefone;
import com.erp.crm.repository.filtro.PessoaFiltro;
import com.erp.crm.repository.filtro.PessoaJuridicaFiltro;

public interface TelefoneService extends GenericService<Telefone,Long> {

	Page<Telefone> listWithPagination(PessoaFiltro pessoaFiltro, Pageable pageable);
	
	List<Telefone> findTelefonePeloIdPessoa(Long id);
	
	Telefone findTelefoneById(Long id);
}
