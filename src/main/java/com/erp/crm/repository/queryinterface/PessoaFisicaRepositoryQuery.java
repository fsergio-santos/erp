package com.erp.crm.repository.queryinterface;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erp.crm.model.PessoaFisica;
import com.erp.crm.repository.filtro.PessoaFisicaFiltro;

public interface PessoaFisicaRepositoryQuery {

	Page<PessoaFisica> listPessoaFisicaWithPagination(PessoaFisicaFiltro pessoaFisicaFiltro, Pageable pageable);
	
	Optional<PessoaFisica> findPessoaFisicaByCpf(String cpf);
}
