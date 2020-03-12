package com.erp.auditory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.auditory.model.PessoaFisicaUsuario;
import com.erp.crm.model.PessoaFisica;

@Repository
public interface PessoaFisicaUsuarioRepository extends JpaRepository<PessoaFisicaUsuario, Long> {

}
