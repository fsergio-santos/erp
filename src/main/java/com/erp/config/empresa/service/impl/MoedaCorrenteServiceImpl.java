package com.erp.config.empresa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.config.empresa.repository.MoedaCorrenteRepository;
import com.erp.config.empresa.repository.filtro.MoedaCorrenteFiltro;
import com.erp.config.empresa.service.MoedaCorrente;

@Service
@Transactional
public class MoedaCorrenteServiceImpl implements MoedaCorrente {

	@Autowired
	private MoedaCorrenteRepository moedaCorrenteRepository;
	
	@Override
	public MoedaCorrente save(MoedaCorrente moedaCorrente) {
		return null;
	}

	@Override
	public MoedaCorrente update(MoedaCorrente moedaCorrente) {
		return null;
	}

	

	@Override
	public MoedaCorrente getOne(Long id) {
		return null;
	}

	@Override
	public Page<MoedaCorrente> listMoedaCorrentePagination(MoedaCorrenteFiltro moedaCorrenteFiltro, Pageable pageable) {
		return null;
	}

	@Override
	public void removeById(Long id) {
	}

	@Override
	public List<MoedaCorrente> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUpdateAuditory(MoedaCorrente entity, String tipoOperacao) {
		// TODO Auto-generated method stub
		
	}

}
