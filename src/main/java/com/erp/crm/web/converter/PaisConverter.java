package com.erp.crm.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.erp.crm.model.Pais;
import com.erp.crm.service.PaisService;

@Component
public class PaisConverter implements Converter<String, Pais>{

	@Autowired
	private PaisService paisService;
	
	@Override
	public Pais convert(String source) {
		if (source.isEmpty()) {
			return null;
		}
		return paisService.getOne(Long.valueOf(source));
	}

}
