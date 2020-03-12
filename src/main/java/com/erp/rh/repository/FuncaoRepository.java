package com.erp.rh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.rh.model.Funcao;

@Repository
public interface FuncaoRepository extends JpaRepository<Funcao, Long> {

}
