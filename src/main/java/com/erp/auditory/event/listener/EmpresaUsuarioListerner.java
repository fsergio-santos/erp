package com.erp.auditory.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erp.auditory.event.EmpresaUsuarioEvent;
import com.erp.auditory.model.EmpresaUsuario;
import com.erp.auditory.repository.EmpresaUsuarioRepository;

@Component
public class EmpresaUsuarioListerner {
	
	@Autowired
	private EmpresaUsuarioRepository empresaUsuarioRepository;
	
	@Transactional
	@EventListener(EmpresaUsuarioEvent.class)
	public void salvarEmpresaUsuario(EmpresaUsuario empresaUsuario) {
		empresaUsuarioRepository.save(empresaUsuario);
	}

}
