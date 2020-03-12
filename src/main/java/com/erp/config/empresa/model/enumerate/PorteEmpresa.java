package com.erp.config.empresa.model.enumerate;

public enum PorteEmpresa {
	
	MICROEMPRESA("Microempresa"),
	PEQUENA("Pequena"),
	MEDIA("Média"),
	GRANDE("Grande");
	
	private String descricao;

	private PorteEmpresa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	

}
