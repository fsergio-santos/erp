package com.erp.auditory.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erp.auditory.event.EmpresaUsuarioEvent;
import com.erp.auditory.event.PessoaFisicaUsuarioEvent;
import com.erp.auditory.model.EmpresaUsuario;
import com.erp.auditory.model.PessoaFisicaUsuario;
import com.erp.auditory.repository.EmpresaUsuarioRepository;
import com.erp.auditory.repository.PessoaFisicaUsuarioRepository;

@Component
public class PessoaFisicaUsuarioListerner {
	
	@Autowired
	private PessoaFisicaUsuarioRepository pessoaFisicaUsuarioRepository;
	
	@Transactional
	@EventListener(PessoaFisicaUsuarioEvent.class)
	public void salvarEmpresaUsuario(PessoaFisicaUsuario pessoaFisicaUsuario) {
		pessoaFisicaUsuarioRepository.save(pessoaFisicaUsuario);
	}

}
