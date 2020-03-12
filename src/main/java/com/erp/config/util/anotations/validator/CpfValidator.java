package com.erp.config.util.anotations.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.erp.config.util.anotations.FindCpf;
import com.erp.crm.model.PessoaFisica;
import com.erp.crm.service.PessoaFisicaService;

    
public class CpfValidator implements ConstraintValidator<FindCpf, String>{

	@Autowired
	private PessoaFisicaService pessoaService;
	
	@Override
	public void initialize(FindCpf cnpjCpf) {
		
	}

	@Override
	public boolean isValid(String cpf, ConstraintValidatorContext constraintValidatorContext) {
    	Optional<PessoaFisica> pessoa = pessoaService.findPessoaByCpf(cpf);
		if (pessoa.isPresent()) {
			return true;
		}
		return false;
	}

	
}
