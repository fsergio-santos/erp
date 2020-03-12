package com.erp.security.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.config.util.data.DataSistema;
import com.erp.security.model.UsuarioSenha;
import com.erp.security.model.dto.UpdatePassword;
import com.erp.security.repository.UsuarioSenhaRepository;
import com.erp.security.security.UsuarioSistema;
import com.erp.security.service.UsuarioSenhaService;



@Service
@Transactional
public class UsuarioSenhaServiceImp implements UsuarioSenhaService {
	
	@Autowired
	private DataSistema dataSistema;

	@Autowired
	private UsuarioSenhaRepository usuarioSenhaRepository;
	
	@Autowired
	private RegistrarUsuarioServiceImpl registrarUsuarioService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public boolean buscarUsuarioSenhaPorId(UpdatePassword password) {
		boolean toReturn = false;
		List<UsuarioSenha> usuarioSenhaBanco = usuarioSenhaRepository.findAll();
		for (UsuarioSenha usrSenha : usuarioSenhaBanco ) {
			if (usrSenha.getSenha().equals(encodeUsuarioPassword(password.getNewPassword()))) {
			   toReturn = true;
			   break;
			}	
		}
		return toReturn;
	}

	@Override
	public UsuarioSenha salvarUsuarioSenha(UsuarioSistema usuario_logado, UpdatePassword password) {
		UsuarioSenha usuarioSenha = new UsuarioSenha();
		usuarioSenha.setSenha(password.getNewPassword());
		//usuarioSenha.setDataUpdate(new Date());
		usuarioSenha.setUsuario(usuario_logado.getUsuario());
		usuarioSenha.getUsuario().setPassword(password.getNewPassword());
		usuarioSenha.getUsuario().setDataVencimentoSenha(dataSistema.somaData(new Date()));
     	usuarioSenhaRepository.saveAndFlush(usuarioSenha);
		registrarUsuarioService.alterarUsuarioSenha(usuario_logado.getUsuario(), password.getNewPassword());
	    return usuarioSenha; 	
	}
	
	
	private String encodeUsuarioPassword(String password) {
		 return passwordEncoder.encode(password);
	}
	
	

}
