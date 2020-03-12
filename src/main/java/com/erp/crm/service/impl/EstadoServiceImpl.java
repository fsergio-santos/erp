package com.erp.crm.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.crm.model.Estado;
import com.erp.crm.repository.EstadoRepository;
import com.erp.crm.repository.filtro.EstadoFiltro;
import com.erp.crm.service.EstadoService;


@Service
@Transactional
public class EstadoServiceImpl implements EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Override
	public Estado save(Estado entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Estado update(Estado entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Estado getOne(Long id) {
		return estadoRepository.getOne(id);
	}

	@Override
	public void removeById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<Estado> listCidadePagination(EstadoFiltro estadoFiltro, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estado> findAll() {
		return estadoRepository.findAll();
	}

	@Override
	public void saveUpdateAuditory(Estado entity, String tipoOperacao) {
		// TODO Auto-generated method stub
		
	}

}
