package com.erp.auditory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.auditory.model.PessoaUsuario;

@Repository
public interface PessoaUsuarioRepository extends JpaRepository<PessoaUsuario, Long>{

}
