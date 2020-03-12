package com.erp.crm.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.crm.model.Endereco;
import com.erp.crm.model.Telefone;
import com.erp.crm.repository.TelefoneRepository;
import com.erp.crm.repository.filtro.PessoaFiltro;
import com.erp.crm.service.TelefoneService;

@Service
@Transactional
public class TelefoneServiceImpl implements TelefoneService {
	
	@Autowired
	private TelefoneRepository telefoneRepository;

	@Override
	public Telefone save(Telefone entity) {
		return telefoneRepository.saveAndFlush(entity);
	}

	@Override
	public Telefone update(Telefone entity) {
		return telefoneRepository.saveAndFlush(entity);
	}

	@Override
	public Telefone getOne(Long id) {
		return telefoneRepository.getOne(id);
	}

	@Override
	public void removeById(Long id) {
		telefoneRepository.deleteById(id);

	}

	@Override
	public Page<Telefone> listWithPagination(PessoaFiltro pessoaFiltro, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Telefone> findAll() {
		return telefoneRepository.findAll();
	}
	
	@Override
	public List<Telefone> findTelefonePeloIdPessoa(Long id) {
		return telefoneRepository.findTelefonePeloIdPessoa(id);
	}
	
	@Override
	public Telefone findTelefoneById(Long id) {
		return telefoneRepository.findTelefoneById(id);
	}

	@Override
	public void saveUpdateAuditory(Telefone entity, String tipoOperacao) {
		// TODO Auto-generated method stub
		
	}

}
