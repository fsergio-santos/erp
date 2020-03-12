package com.erp.auditory.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erp.auditory.event.PessoaUsuarioEvent;
import com.erp.auditory.model.PessoaUsuario;
import com.erp.auditory.repository.PessoaUsuarioRepository;

@Component
public class PessoaUsuarioListener {
	
	@Autowired
	private PessoaUsuarioRepository pessoaUsuarioRepository;
	
	@Transactional
	@EventListener(PessoaUsuarioEvent.class)
    public void salvarPessoaUsuario(PessoaUsuario pessoaUsuario) {
    	pessoaUsuarioRepository.save(pessoaUsuario);
    }
    
}
