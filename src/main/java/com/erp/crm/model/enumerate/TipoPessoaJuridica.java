package com.erp.crm.model.enumerate;

public enum TipoPessoaJuridica {
	
	CLIENTE("Cliente"),
	FORNECEDOR("Fornecedor");
	
	private String descricao;
	
	private TipoPessoaJuridica(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
