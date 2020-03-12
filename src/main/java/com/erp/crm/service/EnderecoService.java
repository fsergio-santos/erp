package com.erp.crm.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.erp.config.util.service.GenericService;
import com.erp.crm.model.Endereco;
import com.erp.crm.repository.filtro.CidadeFiltro;
import com.erp.crm.repository.filtro.EnderecoFiltro;

public interface EnderecoService extends GenericService<Endereco, Long> {
	
	Page<Endereco> listEnderecoPagination(EnderecoFiltro enderecoFiltro, Pageable pageable);
	
	Endereco findEnderecoById(Long id);
	
	List<Endereco> findEnderecoPeloIdPessoa(Long id);

}
