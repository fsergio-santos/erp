package com.erp.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.security.model.Usuario;

@Repository
public interface RegistrarUsuarioRepository extends JpaRepository<Usuario, Long> {

}
