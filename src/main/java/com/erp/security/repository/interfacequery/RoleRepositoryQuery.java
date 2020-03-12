package com.erp.security.repository.interfacequery;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erp.security.model.Role;
import com.erp.security.repository.filtro.RoleFiltro;


public interface RoleRepositoryQuery {
	
    Page<Role> listRoleWithPagination(RoleFiltro roleFiltro, Pageable pageable);
	
	Role findByRole(String role_name);
	
	Optional<Role> buscarRolePeloNome(String nome);

	
}
