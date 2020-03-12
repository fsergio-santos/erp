package com.erp.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.security.model.Role;
import com.erp.security.repository.interfacequery.RoleRepositoryQuery;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, RoleRepositoryQuery {

}
