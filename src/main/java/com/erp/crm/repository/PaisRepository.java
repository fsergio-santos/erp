package com.erp.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.crm.model.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {

}
