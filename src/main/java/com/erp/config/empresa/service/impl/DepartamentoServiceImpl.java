package com.erp.config.empresa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.config.empresa.model.Departamento;
import com.erp.config.empresa.repository.DepartamentoRepository;
import com.erp.config.empresa.repository.filtro.DepartamentoFiltro;
import com.erp.config.empresa.service.DepartamentoService;

@Service
@Transactional
public class DepartamentoServiceImpl implements DepartamentoService {
	
	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Override
	public Departamento save(Departamento departamento) {
		return null;
	}

	@Override
	public Departamento update(Departamento departamento) {
		return null;
	}

	@Override
	public Departamento getOne(Long id) {
		return null;
	}

	@Override
	public Page<Departamento> listDepartamentoPagination(DepartamentoFiltro departamentoFiltro, Pageable pageable) {
		return null;
	}

	@Override
	public void removeById(Long id) {
	}

	@Override
	public List<Departamento> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUpdateAuditory(Departamento entity, String tipoOperacao) {
		// TODO Auto-generated method stub
		
	}

}
