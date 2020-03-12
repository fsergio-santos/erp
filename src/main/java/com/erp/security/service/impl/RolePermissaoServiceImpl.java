package com.erp.security.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.ReportAsSingleViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.security.model.RolePermissao;
import com.erp.security.model.RolePermissaoId;
import com.erp.security.model.dto.ListaRolePermissao;
import com.erp.security.repository.RolePermissaoRepository;
import com.erp.security.repository.filtro.RolePermissaoFiltro;
import com.erp.security.service.RolePermissaoService;



@Service
@Transactional
public class RolePermissaoServiceImpl implements RolePermissaoService {
	
    @Autowired
	private RolePermissaoRepository rolePermissaoRepository;
	
    @Override
	public List<RolePermissao> findAll() {
		return rolePermissaoRepository.findAll();
	}

	@Override
	public RolePermissao save(RolePermissao entity) {
		return rolePermissaoRepository.save(entity);
	}

	@Override
	public RolePermissao update(RolePermissao entity) {
		return save(entity);
	}

	@Override
	public RolePermissao getOne(RolePermissaoId id) {
		return rolePermissaoRepository.getOne(id);
	}

	@Override
	public void removeById(RolePermissaoId id) {
          rolePermissaoRepository.deleteById(id);		
	}
	

	@Override
	@Transactional(readOnly=true)
	public Page<RolePermissao> listRolePermissaoWithPagination(RolePermissaoFiltro rolePermissaoFiltro, Pageable pageable) {
		return rolePermissaoRepository.listRolePermissionPagination(rolePermissaoFiltro, pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public ListaRolePermissao findByRolePermissao(Long role_id, Long escope_id) {
		
		List<RolePermissao> listaPermissao =  rolePermissaoRepository.findByRolePermissao(role_id,escope_id);
        ListaRolePermissao listaRolePermissao = new ListaRolePermissao();
        RolePermissaoId rpId = new RolePermissaoId();
      
        for (RolePermissao rp : listaPermissao) {
        	 rpId.setEscopo_id(rp.getScopeId().getId());
        	 rpId.setPermissao_id(rp.getPermissaoId().getId());
        	 rpId.setRole_id(rp.getRoleId().getId());
        	 listaRolePermissao.setId(rpId); 
             listaRolePermissao.setRole(rp.getRoleId());
             listaRolePermissao.setScope(rp.getScopeId());
             listaRolePermissao.setDataCadastro(rp.getDataCadastro());
             listaRolePermissao.getListaPermissoes().add(rp.getPermissaoId());
        }
    	return listaRolePermissao;
		
	
	}

	@Override
	public void saveUpdateAuditory(RolePermissao entity, String tipoOperacao) {
		// TODO Auto-generated method stub
		
	}

	
    
}
