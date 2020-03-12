package com.erp.crm.model.enumerate;

public enum NaturezaJuridica {

	AUTONOMO("Autônomo"),
	MEI("Micro Empreendedor Individual"),
	INDIVIDUAL("Empresário Individual"),
	SOCIEDADE("Sociedade"),
	COOPERATIVA("Cooperativa");
	
	
	private String descricao;

	private NaturezaJuridica(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	
}
