package com.erp.security.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erp.config.util.service.GenericService;
import com.erp.security.model.Escopo;

public interface EscopeService extends GenericService<Escopo, Long> {

	Page<Escopo> listEscopoPagination(Pageable pageable);

}
