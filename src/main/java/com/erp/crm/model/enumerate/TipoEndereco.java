package com.erp.crm.model.enumerate;

public enum TipoEndereco {

	RESIDENCIAL("Residencial"),
	COMERCIAL("Comercial"),
    ENTREGA("Entrega"),
	COBRANCA("Cobrança");
	
	private String descricao;

	TipoEndereco(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	
	
}
