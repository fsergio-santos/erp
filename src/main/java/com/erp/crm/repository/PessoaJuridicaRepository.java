package com.erp.crm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erp.crm.model.PessoaFisica;
import com.erp.crm.model.PessoaJuridica;
import com.erp.crm.repository.queryinterface.PessoaJuridicaRepositoryQuery;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long>, PessoaJuridicaRepositoryQuery {

	@Query("select pj from PessoaJuridica pj where pj.id=:id")
	Optional<PessoaJuridica> findPessoaJuridicaPeloId(@Param("id")Long id);
}
