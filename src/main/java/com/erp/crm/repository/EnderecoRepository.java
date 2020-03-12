package com.erp.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erp.crm.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	
	
	@Query(value="select e from Endereco e join e.cidade join e.logradouro join e.pessoa where e.id =:id")
	Endereco findEnderecoById(@Param("id") Long id);
	
	@Query(value="select e from Endereco e join e.cidade join e.logradouro join e.pessoa where e.pessoa.id =:id")
	List<Endereco> findEnderecoPeloIdPessoa(@Param("id") Long id);

}
