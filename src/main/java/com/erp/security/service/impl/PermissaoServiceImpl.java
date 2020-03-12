package com.erp.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.erp.security.model.Permissao;
import com.erp.security.repository.PermissaoRepository;
import com.erp.security.repository.filtro.PermissaoFiltro;
import com.erp.security.service.PermissaoService;
import com.erp.security.service.exception.NomePermissaoCadastradoException;

@Service
@Transactional
public class PermissaoServiceImpl implements PermissaoService {

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Override
	public List<Permissao> findAll() {
		return permissaoRepository.findAll();
	}

	@Override
	public Permissao save(Permissao entity) {
        if (buscarPermissaoPeloNome(entity.getNome())) {
        	throw new NomePermissaoCadastradoException("Permissão já foi cadastrada.");
        }
		return permissaoRepository.save(entity);
	}

	@Override
	public Permissao update(Permissao entity) {
		return permissaoRepository.save(entity);
	}

	@Override
	public Permissao getOne(Long id) {
		return permissaoRepository.getOne(id);
	}

	@Override
	public void removeById(Long id) {
       permissaoRepository.deleteById(id);
	}

	@Override
	public Page<Permissao> listPermissaoPagination(PermissaoFiltro permissaoFiltro, Pageable pageable) {
		return permissaoRepository.listPermissaoPagination(permissaoFiltro, pageable);
	}
	
	private boolean buscarPermissaoPeloNome(String nome) {
		return permissaoRepository.buscarPermissaoPeloNome(nome).isPresent() ? true : false;
	}

	@Override
	public boolean fieldValueExists(Object value, String fieldName) {

        if (value == null) {
            return false;
        }
        System.out.println(value.toString());
		return buscarPermissaoPeloNome(value.toString());
	}

	@Override
	public void saveUpdateAuditory(Permissao entity, String tipoOperacao) {
		// TODO Auto-generated method stub
		
	}

}
