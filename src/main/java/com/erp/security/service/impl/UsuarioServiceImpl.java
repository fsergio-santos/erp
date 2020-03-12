package com.erp.security.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.config.util.data.DataSistema;
import com.erp.security.model.Usuario;
import com.erp.security.model.UsuarioSenha;
import com.erp.security.repository.UsuarioRepository;
import com.erp.security.repository.UsuarioSenhaRepository;
import com.erp.security.repository.filtro.UsuarioFiltro;
import com.erp.security.service.UsuarioService;
import com.erp.security.service.exception.EmailUsuarioCadastradoException;
import com.erp.security.service.exception.NomeUsuarioCadastradoException;



@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private DataSistema dataSistema;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    @Autowired
	private UsuarioRepository usuarioRepository;
    
	@Autowired
	private UsuarioSenhaRepository usuarioSenhaRepository;
    
	@Autowired
	private SessionRegistry sessionRegistry;
  
	@Override
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario save(Usuario entity) {
		if (emailExiste(entity.getEmail())) {
			throw new EmailUsuarioCadastradoException("Usuário já cadastrado!");
		}
		if (nomeUsuarioExiste(entity.getUsername())) {
			throw new NomeUsuarioCadastradoException("Nome do usuário já cadastrado!");
		}
		entity.setDataVencimentoSenha(dataSistema.somaData(new Date()));
		entity.setPassword(encodeUsuarioPassword(entity.getPassword()));
        entity.setContraSenha(entity.getPassword());

		entity = usuarioRepository.save(entity);
		
		UsuarioSenha usuarioSenha = new UsuarioSenha();
		usuarioSenha.setSenha(entity.getPassword());
		usuarioSenha.setUsuario(entity);

		usuarioSenhaRepository.save(usuarioSenha);
		
		return entity; 
	}

	@Override
	public Usuario update(Usuario entity) {
		
		entity.setDataVencimentoSenha(dataSistema.somaData(new Date()));
		entity.setPassword(encodeUsuarioPassword(entity.getPassword()));
        entity.setContraSenha(entity.getPassword());

		entity = usuarioRepository.save(entity);
		
		UsuarioSenha usuarioSenha = new UsuarioSenha();
		usuarioSenha.setSenha(entity.getPassword());
		usuarioSenha.setUsuario(entity);

		usuarioSenhaRepository.save(usuarioSenha);
		
		return entity; 
		
	}

	@Override
	public Usuario getOne(Long id) {
		return usuarioRepository.getOne(id);
	}

	@Override
	public void removeById(Long id) {
		usuarioRepository.deleteById(id);
	}


	@Override
	public Optional<Usuario> findUsuarioByEmail(String email) {
		return usuarioRepository.findUsuarioByEmail(email);
	}
	
	@Override
	public Usuario updateRegistroUsuario(Usuario usuario) {
		Usuario usuarioLogado = getOne(usuario.getId());
		usuarioLogado.setLastLogin(usuario.getLastLogin());
		usuarioLogado.setDataVencimentoSenha(usuario.getDataVencimentoSenha());
		return usuarioRepository.save(usuarioLogado);
	}
	
	@Override
	public Usuario findUsuarioAtivoPeloEmail(String email) {
		return usuarioRepository.findUsuarioAtivoPeloEmail(email);
	}

	@Override
	public Usuario findByIdUsuarioRoleAndPermissions(Long id) {
		return usuarioRepository.findByIdUsuarioRoleAndPermissions(id);
	}
	
	
	@Override
	public Page<Usuario> listUsuarioWithPagination(UsuarioFiltro usuarioFiltro, Pageable pageable) {
		return usuarioRepository.listUsuarioWithPagination(usuarioFiltro, pageable);
	}
	
	@Override
    public List<String> getUsuarioFromSessionRegistry() {
        return sessionRegistry.getAllPrincipals().stream().filter((u) -> !sessionRegistry.getAllSessions(u, false).isEmpty()).map(Object::toString).collect(Collectors.toList());
    }

	private boolean emailExiste(String email) {
		return (usuarioRepository.findUsuarioByEmail(email).isPresent() ? true : false);
	}

	private String encodeUsuarioPassword(String password) {
		 return passwordEncoder.encode(password);
	}

	
	private boolean nomeUsuarioExiste(String name) {
		return (usuarioRepository.findUsuarioByName(name).isPresent() ? true : false);
	}

	@Override
	public void saveUpdateAuditory(Usuario entity, String tipoOperacao) {
		// TODO Auto-generated method stub
		
	}
	
}
