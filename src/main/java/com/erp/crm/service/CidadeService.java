package com.erp.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erp.config.util.service.GenericService;
import com.erp.crm.model.Cidade;
import com.erp.crm.repository.filtro.CidadeFiltro;

public interface CidadeService extends GenericService<Cidade, Long>{
	
	Page<Cidade> listCidadePagination(CidadeFiltro cidadeFiltro, Pageable pageable);
	
	List<Cidade> findCidadeByNome(String nome);
	
	

}
