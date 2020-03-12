package com.erp.crm.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.config.empresa.model.Empresa;
import com.erp.config.empresa.repository.filtro.EmpresaFiltro;
import com.erp.config.empresa.service.EmpresaService;
import com.erp.crm.model.Endereco;
import com.erp.crm.repository.EnderecoRepository;
import com.erp.crm.repository.filtro.EnderecoFiltro;
import com.erp.crm.service.EnderecoService;

@Service
@Transactional
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	public Endereco save(Endereco entity) {
		return enderecoRepository.saveAndFlush(entity);
	}

	@Override
	public Endereco update(Endereco entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Endereco getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeById(Long id) {
		enderecoRepository.deleteById(id);
	}

		@Override
	public Page<Endereco> listEnderecoPagination(EnderecoFiltro enderecoFiltro, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Endereco> findAll() {
		return enderecoRepository.findAll();
	}
	
	@Override
	public Endereco findEnderecoById(Long id) {
		return enderecoRepository.findEnderecoById(id);
	}

	@Override
	public List<Endereco> findEnderecoPeloIdPessoa(Long id) {
		return enderecoRepository.findEnderecoPeloIdPessoa(id);
	}

	@Override
	public void saveUpdateAuditory(Endereco entity, String tipoOperacao) {
		// TODO Auto-generated method stub
		
	}
}
