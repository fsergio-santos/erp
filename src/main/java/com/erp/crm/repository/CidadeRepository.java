package com.erp.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erp.crm.model.Cidade;


@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
	@Query(value="select c from Cidade c left join Estado e on c.estado.id = e.id where c.nome like %:nome%")
	List<Cidade> findCidadeByNome(@Param("nome") String nome);
	
                                                                                                                                                     
	

}
