package com.erp.security.service;

import com.erp.security.model.UsuarioSenha;
import com.erp.security.model.dto.UpdatePassword;
import com.erp.security.security.UsuarioSistema;

public interface UsuarioSenhaService {

	boolean buscarUsuarioSenhaPorId(UpdatePassword password);
	
	UsuarioSenha salvarUsuarioSenha(UsuarioSistema usuario_logado, UpdatePassword password);
}
