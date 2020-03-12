package com.erp.crm.model.enumerate;

public enum FormaPessoaJuridica {

	SA("S/A"),
	LTDA("LTDA"),
	ASSOCIACAO("Associação"),
	FUNDACAO("Fundação");
	
	private String descricao;
	
	FormaPessoaJuridica(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}
