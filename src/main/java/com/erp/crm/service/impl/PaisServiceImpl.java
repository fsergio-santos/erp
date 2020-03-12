package com.erp.crm.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.crm.model.Pais;
import com.erp.crm.repository.PaisRepository;
import com.erp.crm.repository.filtro.PaisFiltro;
import com.erp.crm.service.PaisService;

@Service
@Transactional
public class PaisServiceImpl implements PaisService {

	@Autowired
	private PaisRepository paisRepository;
	
	@Override
	public Pais save(Pais entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pais update(Pais entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pais getOne(Long id) {
		return paisRepository.getOne(id);
	}

	@Override
	public void removeById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<Pais> listCidadePagination(PaisFiltro paisFiltro, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pais> findAll() {
		return paisRepository.findAll();
	}

	@Override
	public void saveUpdateAuditory(Pais entity, String tipoOperacao) {
		// TODO Auto-generated method stub
		
	}

}
