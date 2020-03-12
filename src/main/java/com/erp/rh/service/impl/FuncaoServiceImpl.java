package com.erp.rh.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.rh.model.Funcao;
import com.erp.rh.repository.FuncaoRepository;
import com.erp.rh.repository.filtro.FuncaoFiltro;
import com.erp.rh.service.FuncaoService;

@Service
@Transactional
public class FuncaoServiceImpl implements FuncaoService {

	@Autowired
	private FuncaoRepository funcaoRepository;

	@Override
	public Funcao save(Funcao entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Funcao update(Funcao entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Funcao getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<Funcao> listCidadePagination(FuncaoFiltro funcaoFiltro, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Funcao> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUpdateAuditory(Funcao entity, String tipoOperacao) {
		// TODO Auto-generated method stub
		
	}

}
