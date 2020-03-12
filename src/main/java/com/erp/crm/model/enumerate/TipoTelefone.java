package com.erp.crm.model.enumerate;

public enum TipoTelefone {
	
	RESIDENCIAL("Residencial"),
	COMERCIAL("Comercial"),
	PESSOAL("Pessoal");
	
	private String descricao;

	TipoTelefone(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	};
	
	

}
