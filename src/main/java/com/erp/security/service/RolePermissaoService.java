package com.erp.security.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erp.security.model.RolePermissaoId;
import com.erp.security.model.dto.ListaRolePermissao;
import com.erp.security.repository.filtro.RolePermissaoFiltro;
import com.erp.security.model.RolePermissaoId;
import com.erp.config.util.service.GenericService;
import com.erp.security.model.RolePermissao;


public interface RolePermissaoService extends GenericService<RolePermissao, RolePermissaoId>{
	
	
	Page<RolePermissao> listRolePermissaoWithPagination(RolePermissaoFiltro rolePermissaoFiltro, Pageable pageable);
	
	ListaRolePermissao findByRolePermissao(Long role_id, Long escope_id);
	
}
