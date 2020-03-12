package com.erp.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erp.config.util.service.GenericService;
import com.erp.security.model.Role;
import com.erp.security.repository.filtro.RoleFiltro;

public interface RoleService extends GenericService<Role, Long>{
	
	Page<Role> listRoleWithPagination(RoleFiltro roleFiltro, Pageable pageable);
	
	Role findRole(String role);
	
}
