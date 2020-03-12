package com.erp.crm.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.erp.crm.model.Estado;
import com.erp.crm.service.EstadoService;

@Component
public class EstadoConverter implements Converter<String, Estado>{

	@Autowired
	private EstadoService estadoService;
	
	@Override
	public Estado convert(String source) {
		if (source.isEmpty()) {
			return null;
		}
		return estadoService.getOne(Long.valueOf(source));
	}

}
