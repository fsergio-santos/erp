package com.erp.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.security.model.UsuarioSenha;

@Repository
public interface UsuarioSenhaRepository extends JpaRepository<UsuarioSenha, Long> {

}
