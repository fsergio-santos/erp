package com.erp.crm.model.enumerate;

public enum ModeloTelefone {
	
	FIXO("Fixo"),
	CELULAR("Celular");
	
	private String descricao;

	ModeloTelefone(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	

}
