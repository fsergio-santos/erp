package com.erp.crm.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erp.config.util.service.GenericService;
import com.erp.crm.model.Pais;
import com.erp.crm.repository.filtro.PaisFiltro;

public interface PaisService extends GenericService<Pais,Long> {
	
	Page<Pais> listCidadePagination(PaisFiltro paisFiltro, Pageable pageable);

}
