package com.erp.crm.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.erp.config.util.service.GenericService;
import com.erp.crm.model.Pessoa;
import com.erp.crm.model.PessoaFisica;
import com.erp.crm.repository.filtro.LogradouroFiltro;
import com.erp.crm.repository.filtro.PessoaFisicaFiltro;

public interface PessoaFisicaService extends GenericService<PessoaFisica, Long> {
	
	Page<PessoaFisica> listPessoaFisicaPagination(PessoaFisicaFiltro pessoaFisicaFiltro, Pageable pageable);
	
	Optional<PessoaFisica> findPessoaFisicaPeloId(Long id);
	
	Optional<PessoaFisica> findPessoaByCpf(String cpf); 


}
