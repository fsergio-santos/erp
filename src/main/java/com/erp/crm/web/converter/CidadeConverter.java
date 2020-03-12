package com.erp.crm.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.erp.crm.model.Cidade;
import com.erp.crm.service.CidadeService;

@Component
public class CidadeConverter implements Converter<String, Cidade>{

	@Autowired
	private CidadeService cidadeService;
	
	@Override
	public Cidade convert(String source) {
		
		if (source.isEmpty()) {
			return null;
		}
		return cidadeService.getOne(Long.valueOf(source));
	}

}
