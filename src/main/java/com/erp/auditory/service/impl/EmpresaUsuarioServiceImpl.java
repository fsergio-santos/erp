package com.erp.auditory.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.auditory.model.EmpresaUsuario;
import com.erp.auditory.repository.EmpresaUsuarioRepository;
import com.erp.auditory.repository.filtro.EmpresaUsuarioFiltro;
import com.erp.auditory.service.EmpresaUsuarioService;

@Service
@Transactional
public class EmpresaUsuarioServiceImpl implements EmpresaUsuarioService {

	@Autowired
	private EmpresaUsuarioRepository empresaUsuarioRepository;
	
	@Override
	public EmpresaUsuario save(EmpresaUsuario entity) {
		return empresaUsuarioRepository.save(entity);
	}

	@Override
	public EmpresaUsuario update(EmpresaUsuario entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmpresaUsuario getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<EmpresaUsuario> listEmpresaUsuarioWithPagination(EmpresaUsuarioFiltro empresaUsuarioFiltro, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmpresaUsuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUpdateAuditory(EmpresaUsuario entity, String tipoOperacao) {
		// TODO Auto-generated method stub
		
	}

}
