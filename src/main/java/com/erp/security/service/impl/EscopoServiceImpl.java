package com.erp.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.security.model.Escopo;
import com.erp.security.repository.EscopoRepository;
import com.erp.security.service.EscopeService;

@Service
@Transactional
public class EscopoServiceImpl implements EscopeService {

	@Autowired
	private EscopoRepository escopoRepository;
	
	@Override
	public List<Escopo> findAll() {
		return escopoRepository.findAll();
	}

	@Override
	public Escopo save(Escopo entity) {
		return escopoRepository.save(entity);
	}

	@Override
	public Escopo update(Escopo entity) {
		return escopoRepository.save(entity);
	}

	@Override
	public Escopo getOne(Long id) {
		return escopoRepository.getOne(id);
	}

	@Override
	public void removeById(Long id) {
	     escopoRepository.deleteById(id);

	}
	
	@Override
	public Page<Escopo> listEscopoPagination(Pageable pageable) {
		return escopoRepository.listEscopoPagination(pageable);
	}

	@Override
	public void saveUpdateAuditory(Escopo entity, String tipoOperacao) {
		// TODO Auto-generated method stub
		
	}

}
