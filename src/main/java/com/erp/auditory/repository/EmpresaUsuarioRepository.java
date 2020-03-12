package com.erp.auditory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.auditory.model.EmpresaUsuario;

@Repository
public interface EmpresaUsuarioRepository extends JpaRepository<EmpresaUsuario, Long> {

}
