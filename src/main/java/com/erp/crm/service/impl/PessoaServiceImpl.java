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

import com.erp.auditory.event.EmpresaUsuarioEvent;
import com.erp.auditory.event.PessoaUsuarioEvent;
import com.erp.auditory.model.enumerate.TipoOperacao;
import com.erp.crm.model.Pessoa;
import com.erp.crm.repository.PessoaRepository;
import com.erp.crm.repository.filtro.PessoaFiltro;
import com.erp.crm.service.PessoaService;
import com.erp.security.security.UsuarioSistema;

@Service
@Transactional
public class PessoaServiceImpl implements PessoaService {
    
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Override
	public Pessoa save(Pessoa entity) {
		entity = pessoaRepository.save(entity);
		saveUpdateAuditory(entity, TipoOperacao.INCLUSAO.getDescricao());
		return entity;
	}

	@Override
	public Pessoa update(Pessoa entity) {
		entity = pessoaRepository.save(entity);
		saveUpdateAuditory(entity, TipoOperacao.ALTERACAO.getDescricao());
		return entity;
	}

	@Override
	public Pessoa getOne(Long id) {
		return pessoaRepository.getOne(id);
	}

	@Override
	public void removeById(Long id) {
		Pessoa pessoa = getOne(id);
		pessoaRepository.deleteById(id);
		saveUpdateAuditory(pessoa, TipoOperacao.EXCLUSAO.getDescricao());
	}

	@Override
	public Page<Pessoa> listPessoaWithPagination(PessoaFiltro pessoaFiltro, Pageable pageable) {
		return pessoaRepository.listPessoaWithPagination(pessoaFiltro, pageable);
	}

	@Override
	public List<Pessoa> findAll() {
		return pessoaRepository.findAll();
	}

	@Override
	public void saveUpdateAuditory(Pessoa entity, String tipoOperacao) {
		UsuarioSistema usuarioSistema = (UsuarioSistema) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		publisher.publishEvent(new PessoaUsuarioEvent(entity, usuarioSistema.getUsuario(), tipoOperacao));
	}

	
}
