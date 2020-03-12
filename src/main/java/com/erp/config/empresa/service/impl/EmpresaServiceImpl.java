package com.erp.config.empresa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.auditory.event.EmpresaUsuarioEvent;
import com.erp.auditory.model.enumerate.TipoOperacao;
import com.erp.config.empresa.model.Empresa;
import com.erp.config.empresa.repository.EmpresaRepository;
import com.erp.config.empresa.repository.filtro.EmpresaFiltro;
import com.erp.config.empresa.service.EmpresaService;
import com.erp.security.security.UsuarioSistema;

@Service
@Transactional
public class EmpresaServiceImpl implements EmpresaService {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@Override
	public Empresa save(Empresa empresa) {
		empresa = empresaRepository.save(empresa);
		saveUpdateAuditory(empresa, TipoOperacao.INCLUSAO.getDescricao());
		return empresa;
	}

	@Override
	public Empresa update(Empresa empresa) {
		empresa = empresaRepository.save(empresa);
		saveUpdateAuditory(empresa, TipoOperacao.ALTERACAO.getDescricao());
		return empresa;
	}

	@Override
	public Empresa getOne(Long id) {
		return empresaRepository.getOne(id);
	}

	@Override
	public Page<Empresa> listEmpresaPagination(EmpresaFiltro empresaFiltro, Pageable pageable) {
		return null;
	}

	@Override
	public void removeById(Long id) {
		Empresa empresa = getOne(id);
		empresaRepository.deleteById(id);
		saveUpdateAuditory(empresa, TipoOperacao.EXCLUSAO.getDescricao());
	}

	@Override
	public List<Empresa> findAll() {
		return empresaRepository.findAll();
	}
	
	@Override
	public void saveUpdateAuditory(Empresa empresa, String tipoOperacao) {
		UsuarioSistema usuarioSistema = (UsuarioSistema) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		publisher.publishEvent(new EmpresaUsuarioEvent(empresa, usuarioSistema.getUsuario(), tipoOperacao));
	}

}
