package com.erp.security.repository.interfacequery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erp.security.model.Escopo;

public interface EscopoRepositoryQuery {
	
	Page<Escopo> listEscopoPagination(Pageable pageable);

}
