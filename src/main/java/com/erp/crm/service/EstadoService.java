package com.erp.crm.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erp.config.util.service.GenericService;
import com.erp.crm.model.Cidade;
import com.erp.crm.model.Estado;
import com.erp.crm.repository.filtro.CidadeFiltro;
import com.erp.crm.repository.filtro.EstadoFiltro;

public interface EstadoService extends GenericService<Estado, Long> {
	
	Page<Estado> listCidadePagination(EstadoFiltro estadoFiltro, Pageable pageable);

}
