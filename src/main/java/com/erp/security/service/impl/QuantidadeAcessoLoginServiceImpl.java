package com.erp.security.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.erp.security.model.QuantidadesAcessoLogin;
import com.erp.security.model.QuantidadesAcessoLogin_;
import com.erp.security.model.Usuario;
import com.erp.security.repository.QuantidadeAcessoLoginRepository;
import com.erp.security.service.QuantidadeAcessoLoginService;
import com.erp.security.service.UsuarioService;




@Service
@Transactional
public class QuantidadeAcessoLoginServiceImpl implements QuantidadeAcessoLoginService {
	
	private static int MAX_LOGIN_ATTEMPT_ALLOWED = 5;
	

	@Autowired
	private QuantidadeAcessoLoginRepository loginAttemptRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public List<QuantidadesAcessoLogin> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuantidadesAcessoLogin save(QuantidadesAcessoLogin entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuantidadesAcessoLogin update(QuantidadesAcessoLogin entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuantidadesAcessoLogin getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeById(Long id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Optional<QuantidadesAcessoLogin> updateQtdFalhasAcesso(String username){
		 Usuario usuario = usuarioService.findUsuarioAtivoPeloEmail(username);
		 if (!Objects.isNull(usuario) && usuario.getAtivo()) {
			 QuantidadesAcessoLogin login = loginAttemptRepository.findByUsername(username);
			 if (Objects.isNull(login)) {
				 login = new QuantidadesAcessoLogin();
				 login.setUsername(username);
				 login.setQtdFalhasAcesso(1);
			 } else {
				 login.setQtdFalhasAcesso(login.getQtdFalhasAcesso()+1);
			 }
			 login.setDataAcesso(new Date());
			 login.setHoraAcesso(new Date());
			 if (login.getQtdFalhasAcesso() >= MAX_LOGIN_ATTEMPT_ALLOWED) {
				 usuario.setAtivo(false);
				 usuarioService.save(usuario);
			 }
			 return Optional.of(loginAttemptRepository.save(login));
		 }
		 return Optional.empty();
	}
	
	@Override
	public void ressetarQtdFalhasAcesso(String username) {
		QuantidadesAcessoLogin login = loginAttemptRepository.findByUsername(username);
		if (!Objects.isNull(login)) {
			login.setQtdFalhasAcesso(0);
			login.setDataAcesso(new Date());
			login.setHoraAcesso(new Date());
			Usuario usuario = usuarioService.findUsuarioByEmail(username).get();
			usuario.setAtivo(true);
			usuarioService.save(usuario);
		}
	}

	@Override
	public void saveUpdateAuditory(QuantidadesAcessoLogin entity, String tipoOperacao) {
		// TODO Auto-generated method stub
		
	}

}
