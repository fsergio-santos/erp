package com.erp.config.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.erp.config.thymeleaf.processor.PaginationElementTagProcessor;

@Component
public class ErpThymeleafDialect extends AbstractProcessorDialect {

	public ErpThymeleafDialect() {
		super("ERP Sistemas", "erp", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		return processadores;
	}

}
