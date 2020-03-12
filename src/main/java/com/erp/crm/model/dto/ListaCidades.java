package com.erp.crm.model.dto;

import com.erp.crm.model.Estado;

public class ListaCidades {
	
	private Long id;
	private String nome;
	private String sigla;
    private int    ibge;
    private String ddd;
    private String estado;
    private Long idestado;

	public ListaCidades() {

	}

	public ListaCidades(Long id, String nome, String estado) {
		this.id = id;
		this.nome = nome;
		this.estado = estado;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public int getIbge() {
		return ibge;
	}

	public void setIbge(int ibge) {
		this.ibge = ibge;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public Long getIdestado() {
		return idestado;
	}

	public void setIdestado(Long idestado) {
		this.idestado = idestado;
	}
	
	

}
