package com.erp.rh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.rh.model.DepartamentoFuncionarioId;
import com.erp.rh.model.DepartamentoFuncionario;


@Repository
public interface DepartamentoFuncionarioRepository extends JpaRepository<DepartamentoFuncionario, DepartamentoFuncionarioId> {

}
