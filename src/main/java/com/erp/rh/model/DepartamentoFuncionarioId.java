package com.erp.rh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class DepartamentoFuncionarioId implements Serializable{
	
	private Long departamento_id;
	private Long funcionario_id;
	
	@Column(name="departamento_id",insertable=false, updatable=false, nullable=false)
	public Long getDepartamento_id() {
		return departamento_id;
	}
	public void setDepartamento_id(Long departamento_id) {
		this.departamento_id = departamento_id;
	}
	
	@Column(name="funcionario_id",insertable=false, updatable=false, nullable=false)
	public Long getFuncionario_id() {
		return funcionario_id;
	}
	public void setFuncionario_id(Long funcionario_id) {
		this.funcionario_id = funcionario_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((departamento_id == null) ? 0 : departamento_id.hashCode());
		result = prime * result + ((funcionario_id == null) ? 0 : funcionario_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DepartamentoFuncionarioId other = (DepartamentoFuncionarioId) obj;
		if (departamento_id == null) {
			if (other.departamento_id != null)
				return false;
		} else if (!departamento_id.equals(other.departamento_id))
			return false;
		if (funcionario_id == null) {
			if (other.funcionario_id != null)
				return false;
		} else if (!funcionario_id.equals(other.funcionario_id))
			return false;
		return true;
	}
	
	
	

}
