package com.erp.security.repository.interfacequery;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erp.security.model.Usuario;
import com.erp.security.repository.filtro.UsuarioFiltro;

public interface UsuarioRepositoryQuery {
    
	Optional<Usuario> findUsuarioByEmail(String email);
	
	Optional<Usuario> findUsuarioByName(String name);

	Usuario findUsuarioAtivoPeloEmail(String email);
	
	Usuario findByIdUsuarioRoleAndPermissions(Long id);
	
	Page<Usuario> listUsuarioWithPagination(UsuarioFiltro usuarioFiltro, Pageable pageable);
}
