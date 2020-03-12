package com.erp.security.repository.interfacequery;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erp.security.model.RolePermissao;
import com.erp.security.repository.filtro.RolePermissaoFiltro;


public interface RolePermissaoRepositoryQuery {
	
	Page<RolePermissao> listRolePermissionPagination(RolePermissaoFiltro pessoaFiltro, Pageable pageable);
	
	List<RolePermissao> findByRolePermissao(Long role_id, Long escope_id);

}
