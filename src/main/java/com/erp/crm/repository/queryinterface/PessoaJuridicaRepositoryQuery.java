package com.erp.crm.repository.queryinterface;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erp.crm.model.PessoaFisica;
import com.erp.crm.model.PessoaJuridica;
import com.erp.crm.repository.filtro.PessoaFisicaFiltro;
import com.erp.crm.repository.filtro.PessoaJuridicaFiltro;

public interface PessoaJuridicaRepositoryQuery {

	Page<PessoaJuridica> listPessoaJuridicaWithPagination(PessoaJuridicaFiltro pessoaJuridicaFiltro, Pageable pageable);
	
	Optional<PessoaJuridica> findPessoaJuridicaByCnpj(String cnpj);
}
