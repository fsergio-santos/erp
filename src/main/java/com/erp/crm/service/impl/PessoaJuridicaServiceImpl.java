package com.erp.crm.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.erp.crm.model.PessoaJuridica;
import com.erp.crm.repository.PessoaJuridicaRepository;
import com.erp.crm.repository.filtro.PessoaJuridicaFiltro;
import com.erp.crm.service.PessoaJuridicaService;

@Service
@Transactional
public class PessoaJuridicaServiceImpl implements PessoaJuridicaService {

	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;
	
	@Override
	public PessoaJuridica save(PessoaJuridica entity) {
		return pessoaJuridicaRepository.save(entity);
	}

	@Override
	public PessoaJuridica update(PessoaJuridica entity) {
		return save(entity);
	}

	@Override
	public PessoaJuridica getOne(Long id) {
		return pessoaJuridicaRepository.getOne(id);
	}

	@Override
	public void removeById(Long id) {
		pessoaJuridicaRepository.deleteById(id);

	}

	@Override
	public Page<PessoaJuridica> listPessoaJuridicaPagination(PessoaJuridicaFiltro pessoaJuridicaFiltro, Pageable pageable) {
		return pessoaJuridicaRepository.listPessoaJuridicaWithPagination(pessoaJuridicaFiltro, pageable);
	}

	@Override
	public List<PessoaJuridica> findAll() {
		return pessoaJuridicaRepository.findAll();
	}
	
	@Override
	public Optional<PessoaJuridica> findPessoaJuridicaPeloId(Long id) {
		return pessoaJuridicaRepository.findPessoaJuridicaPeloId(id);
	}

	@Override
	public void saveUpdateAuditory(PessoaJuridica entity, String tipoOperacao) {
		// TODO Auto-generated method stub
		
	}

}
