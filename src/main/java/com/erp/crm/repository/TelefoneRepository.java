package com.erp.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erp.crm.model.Telefone;


@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
	
	@Query(value="select t from Telefone t join t.pessoa where t.pessoa.id =:id")
	List<Telefone> findTelefonePeloIdPessoa(@Param("id") Long id);

	@Query(value="select t from Telefone t join t.pessoa where t.id =:id")
	Telefone findTelefoneById(@Param("id") Long id);

	
}
