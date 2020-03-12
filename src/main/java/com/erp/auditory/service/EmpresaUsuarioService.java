package com.erp.auditory.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erp.auditory.model.EmpresaUsuario;
import com.erp.auditory.repository.filtro.EmpresaUsuarioFiltro;
import com.erp.config.util.service.GenericService;

public interface EmpresaUsuarioService extends GenericService<EmpresaUsuario, Long> {
	
	public Page<EmpresaUsuario> listEmpresaUsuarioWithPagination(EmpresaUsuarioFiltro empresaUsuarioFiltro, Pageable pageable);

}
