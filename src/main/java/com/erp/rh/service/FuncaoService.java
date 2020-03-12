package com.erp.rh.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erp.config.util.service.GenericService;
import com.erp.crm.model.PessoaJuridica;
import com.erp.crm.repository.filtro.PessoaJuridicaFiltro;
import com.erp.rh.model.Funcao;
import com.erp.rh.repository.filtro.FuncaoFiltro;

public interface FuncaoService extends GenericService<Funcao,Long> {
	
	Page<Funcao> listCidadePagination(FuncaoFiltro funcaoFiltro, Pageable pageable);

}
