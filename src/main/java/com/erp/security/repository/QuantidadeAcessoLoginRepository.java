package com.erp.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.security.model.QuantidadesAcessoLogin;


@Repository
public interface QuantidadeAcessoLoginRepository extends JpaRepository<QuantidadesAcessoLogin, Long> {

	QuantidadesAcessoLogin findByUsername(String username);
	
}
