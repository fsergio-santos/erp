package com.erp.crm.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.crm.model.Logradouro;
import com.erp.crm.repository.LogradouroRepository;
import com.erp.crm.repository.filtro.LogradouroFiltro;
import com.erp.crm.service.LogradouroService;

@Service
@Transactional
public class LogradouroServiceImpl implements LogradouroService {

	@Autowired
	private LogradouroRepository logradouroRepository;
	
	@Override
	public Logradouro save(Logradouro entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Logradouro update(Logradouro entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Logradouro getOne(Long id) {
		return logradouroRepository.getOne(id);
	}

	@Override
	public void removeById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<Logradouro> listLogradouroPagination(LogradouroFiltro logradouroFiltro, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Logradouro> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Logradouro retrieveLogradouroByCep(String cep) {
		System.out.println("chegando no service");
		return logradouroRepository.findLogradouroByCep(cep);
	}

	@Override
	public void saveUpdateAuditory(Logradouro entity, String tipoOperacao) {
		// TODO Auto-generated method stub
		
	}
}
