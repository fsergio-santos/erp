package com.erp.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.security.model.Usuario;
import com.erp.security.repository.interfacequery.UsuarioRepositoryQuery;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQuery {

}
