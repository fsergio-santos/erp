package com.erp.crm.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.auditory.event.PessoaUsuarioEvent;
import com.erp.auditory.model.enumerate.TipoOperacao;
import com.erp.crm.model.Pessoa;
import com.erp.crm.model.PessoaFisica;
import com.erp.crm.repository.PessoaFisicaRepository;
import com.erp.crm.repository.filtro.PessoaFisicaFiltro;
import com.erp.crm.service.PessoaFisicaService;
import com.erp.crm.service.exceptions.CnpjCpfExistente;
import com.erp.security.security.UsuarioSistema;

@Service
@Transactional
public class PessoaFisicaServiceImpl implements PessoaFisicaService {

	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Override
	public PessoaFisica save(PessoaFisica entity) {
		Optional<PessoaFisica> optionalPessoaFisica = findPessoaByCpf(entity.getCpf());
		if (optionalPessoaFisica.isPresent()){
			throw new CnpjCpfExistente("CPF já foi cadastrado!");
		}
		entity=pessoaFisicaRepository.save(entity);
		saveUpdateAuditory(entity, TipoOperacao.INCLUSAO.getDescricao());
		return entity;
	}

	@Override
	public PessoaFisica update(PessoaFisica entity) {
		entity=pessoaFisicaRepository.save(entity);
		saveUpdateAuditory(entity, TipoOperacao.ALTERACAO.getDescricao());
		return entity;
	}

	@Override
	public PessoaFisica getOne(Long id) {
		return pessoaFisicaRepository.getOne(id);
	}

	@Override
	public void removeById(Long id) {
		PessoaFisica pessoaFisica = getOne(id);
		pessoaFisicaRepository.deleteById(id);
		saveUpdateAuditory(pessoaFisica, TipoOperacao.EXCLUSAO.getDescricao());
	}

	
	@Override
	public Page<PessoaFisica> listPessoaFisicaPagination(PessoaFisicaFiltro pessoaFisicaFiltro, Pageable pageable) {
		return pessoaFisicaRepository.listPessoaFisicaWithPagination(pessoaFisicaFiltro, pageable);
	}

	@Override
	public List<PessoaFisica> findAll() {
		return pessoaFisicaRepository.findAll();
	}
	
	@Override
	public Optional<PessoaFisica> findPessoaFisicaPeloId(Long id) {
		return pessoaFisicaRepository.findPessoaFisicaPeloId(id);
	}
	
	@Override
	public Optional<PessoaFisica> findPessoaByCpf(String cpf) {
		return pessoaFisicaRepository.findPessoaFisicaByCpf(cpf);
	}

	@Override
	public void saveUpdateAuditory(PessoaFisica entity, String tipoOperacao) {
		UsuarioSistema usuarioSistema = (UsuarioSistema) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		publisher.publishEvent(new PessoaUsuarioEvent(entity, usuarioSistema.getUsuario(), tipoOperacao));
	}


}
