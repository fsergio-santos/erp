package com.erp.config.util.anotations.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.erp.config.util.anotations.ExisteNome;
import com.erp.config.util.anotations.FieldValueExists;

public class NomeValidator implements ConstraintValidator<ExisteNome, Object>{
	
	@Autowired
	private ApplicationContext applicationContext;

	private FieldValueExists service;
	private String fieldName;

	@Override
	public void initialize(ExisteNome existeNome) {
		Class<? extends FieldValueExists> clazz = existeNome.service();
        this.fieldName = existeNome.fieldName();
        String serviceQualifier = existeNome.serviceQualifier();

        if (!serviceQualifier.equals("")) {
            this.service = this.applicationContext.getBean(serviceQualifier, clazz);
        } else {
            this.service = this.applicationContext.getBean(clazz);
        }
	}
	
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		return !this.service.fieldValueExists(value, this.fieldName);
	}

}
