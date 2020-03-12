package com.erp.crm.model.enumerate;

public enum RegimeCasamento {
	
	COMUNHAO_TOTAL_BENS("Comunhão Total de Bens"),
	COMUNHAO_PARCIAL_BENS("Comunhão Parcial de Bens"),
	SEPARAÇÃO_BENS("Separação total de Bens"),
	PARTICIPACAO_FINAL_AQUESTOS("Participação Final Aquestos");
	
	private String descricao;

	RegimeCasamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	
	
	

}
