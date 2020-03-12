package com.erp.crm.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erp.config.util.service.GenericService;
import com.erp.crm.model.Pessoa;
import com.erp.crm.model.PessoaJuridica;
import com.erp.crm.repository.filtro.PessoaFiltro;


public interface PessoaService extends GenericService<Pessoa,Long> {
	
	Page<Pessoa> listPessoaWithPagination(PessoaFiltro pessoaFiltro, Pageable pageable);

}
