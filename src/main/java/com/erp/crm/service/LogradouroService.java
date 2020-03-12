package com.erp.crm.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erp.config.util.service.GenericService;
import com.erp.crm.model.Logradouro;
import com.erp.crm.repository.filtro.CidadeFiltro;
import com.erp.crm.repository.filtro.LogradouroFiltro;

public interface LogradouroService extends GenericService<Logradouro, Long> {
	
	Page<Logradouro> listLogradouroPagination(LogradouroFiltro logradouroFiltro, Pageable pageable);

	Logradouro retrieveLogradouroByCep(String cep);
}
