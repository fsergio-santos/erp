package com.erp.crm.model.enumerate;

public enum TipoPessoa {
	FISICA("Fisíca"),
	JURIDICA("Jurídica");
	
	private String descricao;

	private TipoPessoa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	
}
