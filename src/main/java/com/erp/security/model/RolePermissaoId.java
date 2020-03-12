package com.erp.security.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RolePermissaoId implements Serializable {
	
	private static final long serialVersionUID = -5838205134618363082L;

	private Long permissao_id;
	private Long role_id;
	private Long escopo_id;
	
	
	public RolePermissaoId() {
		super();
	}
	
	@Column(name="permissao_id",insertable=false, updatable=false, nullable=false)
	public Long getPermissao_id() {
		return permissao_id;
	}
	public void setPermissao_id(Long permissao_id) {
		this.permissao_id = permissao_id;
	}
	
	@Column(name="role_id",insertable=false, updatable=false, nullable=false)
	public Long getRole_id() {
		return role_id;
	}
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}
	
	@Column(name="escopo_id",insertable=false, updatable=false, nullable=false)	
	public Long getEscopo_id() {
		return escopo_id;
	}

	public void setEscopo_id(Long escopo_id) {
		this.escopo_id = escopo_id;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((escopo_id == null) ? 0 : escopo_id.hashCode());
		result = prime * result + ((permissao_id == null) ? 0 : permissao_id.hashCode());
		result = prime * result + ((role_id == null) ? 0 : role_id.hashCode());
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
		RolePermissaoId other = (RolePermissaoId) obj;
		if (escopo_id == null) {
			if (other.escopo_id != null)
				return false;
		} else if (!escopo_id.equals(other.escopo_id))
			return false;
		if (permissao_id == null) {
			if (other.permissao_id != null)
				return false;
		} else if (!permissao_id.equals(other.permissao_id))
			return false;
		if (role_id == null) {
			if (other.role_id != null)
				return false;
		} else if (!role_id.equals(other.role_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RolePermissaoId [permissao_id=" + permissao_id + ", role_id=" + role_id + ", escopo_id=" + escopo_id
				+ "]";
	}

	

}
