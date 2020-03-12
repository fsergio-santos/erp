package com.erp.crm.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.erp.crm.model.Pessoa;
import com.erp.crm.service.PessoaService;

@Component
public class PessoaConverter implements Converter<String, Pessoa>{
    
	@Autowired
	private PessoaService pessoaService;
	
	@Override
	public Pessoa convert(String source) {
		if (source.isEmpty()) {
			return null;
		}
		return pessoaService.getOne(Long.valueOf(source));
	}

}
