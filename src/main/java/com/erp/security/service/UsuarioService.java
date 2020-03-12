package com.erp.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erp.config.util.service.GenericService;
import com.erp.security.model.Usuario;
import com.erp.security.repository.filtro.UsuarioFiltro;


public interface UsuarioService extends GenericService<Usuario, Long>{

    Optional<Usuario> findUsuarioByEmail(String email);

    Usuario updateRegistroUsuario(Usuario usuario);
    
    Usuario findUsuarioAtivoPeloEmail(String email);

	Usuario findByIdUsuarioRoleAndPermissions(Long id);

	Page<Usuario> listUsuarioWithPagination(UsuarioFiltro usuarioFiltro, Pageable pageable);
	
	List<String> getUsuarioFromSessionRegistry();
}
