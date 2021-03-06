
package com.erp.security.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.erp.auditory.listener.AuditoriaListerner;
import com.erp.auditory.model.Auditoria;
import com.erp.auditory.model.RegistroSistema;

@Entity
@Table(name="TAB_ROLE_PERMISSAO")
@EntityListeners(AuditoriaListerner.class)
public class RolePermissao implements Serializable, Auditoria {
		
    private RolePermissaoId id;
	
	private Permissao permissaoId;
	private Role roleId;
	private Escopo scopeId;
	
	private Date dataCadastro;
	
	private RegistroSistema registroSistema;
	
			
	public RolePermissao() {
		super();
	}

	public RolePermissao(RolePermissaoId id, 
			             Permissao permissaoId, 
			             Role roleId,  
			             Escopo scopeId){
		super();
		this.id = id;
		this.permissaoId = permissaoId;
		this.roleId = roleId;
		this.scopeId = scopeId;


	}
	
    @EmbeddedId	
	public RolePermissaoId getId() {
		return id;
	}
	public void setId(RolePermissaoId id) {
		this.id = id;
	}
	
    @ManyToOne
	@JoinColumn(name="permissao_id",nullable=false,insertable=false, updatable=false)
	public Permissao getPermissaoId() {
		return permissaoId;
	}
	public void setPermissaoId(Permissao permissaoId) {
		this.permissaoId = permissaoId;
	}
	
    @ManyToOne
	@JoinColumn(name="role_id",nullable=false,insertable=false, updatable=false)
	public Role getRoleId() {
		return roleId;
	}
	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}
	
	@ManyToOne
	@JoinColumn(name="escopo_id",nullable=false,insertable=false, updatable=false)
    public Escopo getScopeId() {
		return scopeId;
	}

	public void setScopeId(Escopo scopeId) {
		this.scopeId = scopeId;
	}

	@NotNull(message="A data de cadastro deve ser informada.")
	@DateTimeFormat(iso = ISO.DATE, pattern = "")
	@Temporal(TemporalType.DATE)
	@Column(name="data_cadastro", nullable=false, columnDefinition = "DATE")
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Embedded
	@Override
	public RegistroSistema getRegistroSistema() {
		return new RegistroSistema();
	}

	@Override
	public void setRegistroSistema(RegistroSistema registroSistema) {
		this.registroSistema = registroSistema;
		
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		RolePermissao other = (RolePermissao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RolePermissao [id=" + id + ", permissaoId=" + permissaoId.getId() + ", roleId=" + roleId.getId() + ", scopeId="
				+ scopeId + ", dataCadastro=" + dataCadastro + "]";
	}
	
	
	

}
