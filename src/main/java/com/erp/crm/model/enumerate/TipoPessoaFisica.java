package com.erp.crm.model.enumerate;

public enum TipoPessoaFisica {
	
	CLIENTE("Cliente"),
	FUNCIONARIO("Funcionário");
	
	private String descricao;
	
	TipoPessoaFisica(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	

}
