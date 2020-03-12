package com.erp.security.repository.interfacequery;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erp.security.model.Escopo;
import com.erp.security.model.Permissao;
import com.erp.security.repository.filtro.PermissaoFiltro;

public interface PermissaoRepositoryQuery {
	
	Page<Permissao> listPermissaoPagination(PermissaoFiltro permissalFiltro, Pageable pageable);
	
	Optional<Permissao> buscarPermissaoPeloNome(String nome);

}
