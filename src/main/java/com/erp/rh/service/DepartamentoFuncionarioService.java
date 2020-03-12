package com.erp.rh.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erp.config.util.service.GenericService;
import com.erp.crm.model.PessoaJuridica;
import com.erp.rh.model.DepartamentoFuncionario;
import com.erp.rh.model.DepartamentoFuncionarioId;
import com.erp.rh.repository.filtro.DepartamentoFuncionarioFiltro;

public interface DepartamentoFuncionarioService extends GenericService<DepartamentoFuncionario, DepartamentoFuncionarioId> {

	Page<DepartamentoFuncionario> listCidadePagination(DepartamentoFuncionarioFiltro pessoaJuridicaFiltro, Pageable pageable);
}
