package com.erp.crm.service.exceptions;

public class CnpjCpfExistente extends RuntimeException {

	private static final long serialVersionUID = -3881349122130517648L;
	
	public CnpjCpfExistente(String message) {
		super(message);
	}

}
