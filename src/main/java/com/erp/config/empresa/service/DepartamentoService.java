package com.erp.config.empresa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erp.config.empresa.model.Departamento;
import com.erp.config.empresa.repository.filtro.DepartamentoFiltro;
import com.erp.config.util.service.GenericService;

public interface DepartamentoService extends GenericService<Departamento, Long>{
	
	Page<Departamento> listDepartamentoPagination(DepartamentoFiltro departamentoFiltro, Pageable pageable);

}
