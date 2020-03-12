package com.erp.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erp.crm.model.Logradouro;


@Repository
public interface LogradouroRepository extends JpaRepository<Logradouro, Long> {

	Logradouro findByDescricao(String descricao);
	
	@Query(value="select l from Logradouro l left join Cidade c on l.cidade.id = c.id where l.cep = :cep")
	Logradouro findLogradouroByCep(@Param("cep") String cep);

}
