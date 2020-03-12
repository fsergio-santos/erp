package com.erp.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.security.model.Escopo;
import com.erp.security.repository.interfacequery.EscopoRepositoryQuery;

@Repository
public interface EscopoRepository extends JpaRepository<Escopo, Long>, EscopoRepositoryQuery{

}
