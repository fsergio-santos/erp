package com.erp.security.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.security.model.Role;
import com.erp.security.repository.RoleRepository;
import com.erp.security.repository.filtro.RoleFiltro;
import com.erp.security.service.RoleService;
import com.erp.security.service.exception.NomeRoleCadastradaException;


@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	
    @Autowired
	private RoleRepository roleRepository;
    
    @Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role save(Role entity) {
		if (buscarRolePeloNome(entity.getNome())) {
			throw new NomeRoleCadastradaException("Essa Role já foi cadastrada.");
		}
		return roleRepository.save(entity);
	}

	@Override
	public Role update(Role entity) { 
		return roleRepository.save(entity);
	}

	@Override
	public Role getOne(Long id) {
		return roleRepository.getOne(id);
	}

	@Override
	public void removeById(Long id) {
		roleRepository.deleteById(id);
	}

	@Override
	public Role findRole(String role) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Page<Role> listRoleWithPagination(RoleFiltro roleFiltro, Pageable pageable) {
		return roleRepository.listRoleWithPagination(roleFiltro, pageable);
	}

	
	private boolean buscarRolePeloNome(String nome) {
		return roleRepository.buscarRolePeloNome(nome).isPresent() ? true : false;
	}

	@Override
	public void saveUpdateAuditory(Role entity, String tipoOperacao) {
		// TODO Auto-generated method stub
		
	}
	

}
