package com.erp.security.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erp.config.util.anotations.FieldValueExists;
import com.erp.config.util.service.GenericService;
import com.erp.security.model.Escopo;
import com.erp.security.model.Permissao;
import com.erp.security.repository.filtro.PermissaoFiltro;

public interface PermissaoService extends GenericService<Permissao, Long>, FieldValueExists {

	Page<Permissao> listPermissaoPagination(PermissaoFiltro permissaoFiltro, Pageable pageable);
	
}
