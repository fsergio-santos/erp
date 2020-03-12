package com.erp.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.crm.model.Estado;


@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

	Estado findByNome(String nomeEstado);

}
