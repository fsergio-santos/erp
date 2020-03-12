package com.erp.crm.model.enumerate;

public enum StatusTelefone {

	RESIDENCIAL("Residencial"),
	COMERCIAL("Comercial");
	
	private String descricao;

	StatusTelefone(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
