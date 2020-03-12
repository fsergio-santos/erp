package com.erp.config.empresa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erp.config.empresa.model.Empresa;
import com.erp.config.empresa.repository.filtro.EmpresaFiltro;
import com.erp.config.util.service.GenericService;

public interface EmpresaService extends GenericService<Empresa, Long>{

	Page<Empresa> listEmpresaPagination(EmpresaFiltro empresaFiltro, Pageable pageable);
	
}
