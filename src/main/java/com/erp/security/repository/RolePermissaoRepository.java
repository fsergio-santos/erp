package com.erp.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.security.model.RolePermissao;
import com.erp.security.model.RolePermissaoId;
import com.erp.security.repository.interfacequery.RolePermissaoRepositoryQuery;


@Repository
public interface RolePermissaoRepository extends JpaRepository<RolePermissao, RolePermissaoId>, RolePermissaoRepositoryQuery {

}
