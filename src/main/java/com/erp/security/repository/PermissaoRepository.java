package com.erp.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.security.model.Permissao;
import com.erp.security.repository.interfacequery.PermissaoRepositoryQuery;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long>, PermissaoRepositoryQuery{



}
