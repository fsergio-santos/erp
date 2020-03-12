package com.erp.rh.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.rh.model.DepartamentoFuncionario;
import com.erp.rh.model.DepartamentoFuncionarioId;
import com.erp.rh.repository.DepartamentoFuncionarioRepository;
import com.erp.rh.repository.filtro.DepartamentoFuncionarioFiltro;
import com.erp.rh.service.DepartamentoFuncionarioService;

@Service
@Transactional
public class DepartamentoFuncionarioServiceImpl implements DepartamentoFuncionarioService {

	@Autowired
	private DepartamentoFuncionarioRepository departamentoFuncionarioRepository;
	
	@Override
	public DepartamentoFuncionario save(DepartamentoFuncionario entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DepartamentoFuncionario update(DepartamentoFuncionario entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DepartamentoFuncionario getOne(DepartamentoFuncionarioId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeById(DepartamentoFuncionarioId id) {
		// TODO Auto-generated method stub

	}


	@Override
	public Page<DepartamentoFuncionario> listCidadePagination(DepartamentoFuncionarioFiltro pessoaJuridicaFiltro,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DepartamentoFuncionario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUpdateAuditory(DepartamentoFuncionario entity, String tipoOperacao) {
		// TODO Auto-generated method stub
		
	}

}
