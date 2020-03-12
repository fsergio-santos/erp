package com.erp.config.empresa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erp.config.empresa.repository.filtro.MoedaCorrenteFiltro;
import com.erp.config.util.service.GenericService;


public interface MoedaCorrente extends GenericService<MoedaCorrente, Long>{
	
	Page<MoedaCorrente> listMoedaCorrentePagination(MoedaCorrenteFiltro moedaCorrenteFiltro, Pageable pageable);

}
