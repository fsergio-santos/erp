package com.erp.crm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erp.crm.model.Pessoa;
import com.erp.crm.model.PessoaFisica;
import com.erp.crm.repository.queryinterface.PessoaFisicaRepositoryQuery;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long>, PessoaFisicaRepositoryQuery{
	
	@Query("select pf from PessoaFisica pf where pf.id=:id")
	Optional<PessoaFisica> findPessoaFisicaPeloId(@Param("id")Long id);

}
