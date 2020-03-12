package com.erp.crm.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.erp.crm.model.Logradouro;
import com.erp.crm.service.LogradouroService;

@Component
public class LogradouroConverter implements Converter<String, Logradouro>{

	@Autowired
	private LogradouroService logradouroService;
	
	@Override
	public Logradouro convert(String source) {
		if (source.isEmpty()) {
			return null;
		}
		return logradouroService.getOne(Long.valueOf(source));
	}

}
