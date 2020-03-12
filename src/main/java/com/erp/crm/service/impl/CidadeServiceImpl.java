package com.erp.crm.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.erp.crm.model.Cidade;
import com.erp.crm.repository.CidadeRepository;
import com.erp.crm.repository.filtro.CidadeFiltro;
import com.erp.crm.service.CidadeService;

@Service
@Transactional
public class CidadeServiceImpl implements CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Override
	public Cidade save(Cidade entity) {
		return null;
	}

	@Override
	public Cidade update(Cidade entity) {
		return null;
	}

	@Override
	public Cidade getOne(Long id) {
		return cidadeRepository.getOne(id);
	}

	@Override
	public void removeById(Long id) {
		
	}

	@Override
	public Page<Cidade> listCidadePagination(CidadeFiltro cidadeFiltro, Pageable pageable) {
    	return null;
	}
	
	
	@Override
	public List<Cidade> findAll() {
		return cidadeRepository.findAll();
	}

	@Override
	public List<Cidade> findCidadeByNome(String nome) {
		System.out.println("passando pelo service");
		return cidadeRepository.findCidadeByNome(nome);
	}

	@Override
	public void saveUpdateAuditory(Cidade entity, String tipoOperacao) {
		// TODO Auto-generated method stub
		
	}

}
