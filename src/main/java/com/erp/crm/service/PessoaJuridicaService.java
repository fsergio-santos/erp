package com.erp.crm.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.erp.config.util.service.GenericService;
import com.erp.crm.model.PessoaFisica;
import com.erp.crm.model.PessoaJuridica;
import com.erp.crm.repository.filtro.PessoaFisicaFiltro;
import com.erp.crm.repository.filtro.PessoaJuridicaFiltro;

public interface PessoaJuridicaService extends GenericService<PessoaJuridica, Long> {
   
	Page<PessoaJuridica> listPessoaJuridicaPagination(PessoaJuridicaFiltro pessoaJuridicaFiltro, Pageable pageable);
	
	Optional<PessoaJuridica> findPessoaJuridicaPeloId(Long id);
}
