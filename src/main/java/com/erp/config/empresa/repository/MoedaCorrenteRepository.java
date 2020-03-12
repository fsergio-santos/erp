package com.erp.config.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.config.empresa.model.MoedaCorrente;

@Repository
public interface MoedaCorrenteRepository extends JpaRepository<MoedaCorrente, Long> {

}
