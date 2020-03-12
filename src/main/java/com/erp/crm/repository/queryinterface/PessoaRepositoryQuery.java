package com.erp.crm.repository.queryinterface;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erp.crm.model.Pessoa;
import com.erp.crm.model.Pessoa_;
import com.erp.crm.repository.filtro.PessoaFiltro;


public interface PessoaRepositoryQuery {
	
	Page<Pessoa> listPessoaWithPagination(PessoaFiltro pessoaFiltro, Pageable pageable);
	


}
