package com.erp.config.util.service;

import java.util.List;
import java.util.Optional;

import com.erp.config.empresa.model.Empresa;
import com.erp.crm.model.Cidade;

public interface GenericService<T, ID> {
	
	List<T> findAll();
	
	T save(T entity);

	T update(T entity);

	T getOne(ID id);
	
	void removeById(ID id);
	
	void saveUpdateAuditory(T entity, String tipoOperacao);

}
