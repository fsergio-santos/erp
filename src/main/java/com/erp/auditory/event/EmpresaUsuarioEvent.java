package com.erp.auditory.event;

import java.util.Date;

import org.springframework.context.ApplicationEvent;

import com.erp.auditory.model.EmpresaUsuario;
import com.erp.config.empresa.model.Empresa;
import com.erp.security.model.Usuario;

public class EmpresaUsuarioEvent {

	private EmpresaUsuario empresaUsuario;
	
	public EmpresaUsuarioEvent(Empresa empresa, Usuario usuario, String tipoOperacao) {
		empresaUsuario = new EmpresaUsuario(empresa, usuario, new Date(), tipoOperacao);
	}
	
	
	public EmpresaUsuario getEmpresaUsuario() {
		return empresaUsuario;
	}
	
	

}
