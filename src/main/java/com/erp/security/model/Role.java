package com.erp.security.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;

import com.erp.auditory.listener.AuditoriaListerner;
import com.erp.auditory.model.Auditoria;
import com.erp.auditory.model.RegistroSistema;
import com.erp.config.util.ErpConfigure;


@Entity
@Table(name = "TAB_ROLE")
@SequenceGenerator(name="role_sequence",sequenceName="tab_role_sequence", initialValue = 1, allocationSize = 1)
@SQLDelete(sql = "UPDATE TAB_ROLE SET registro_deletado = true WHERE role_id = ?")
@Where(clause=ErpConfigure.NAO_DELETADO)
@EntityListeners(AuditoriaListerner.class)
public class Role implements Serializable, Auditoria {


	private Long id;
    private String nome;
    private List<Usuario> usuarios;
    private List<RolePermissao> rolePermissao;
    private RegistroSistema registroSistema;
    
    public Role() {
	}


	public Role(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
    
    
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="role_sequence")
    @Column(name = "role_id")
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Size(max = 50, min = 3, message="O nome da regra deve ter entre {min} e {max} caracteres.")
	@NotBlank(message="Informe o nome da pessoa.")
	@NotNull(message = "O campo nome não pode ser nulo.")
	@Column(name="role_name",length=50, nullable=false)
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Where(clause=ErpConfigure.NAO_DELETADO)
	@ManyToMany(mappedBy="roles")
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	@Where(clause=ErpConfigure.NAO_DELETADO)
	@OneToMany(mappedBy="roleId",fetch=FetchType.LAZY)
	public List<RolePermissao> getRolePermissao() {
		return rolePermissao;
	}


	public void setRolePermissao(List<RolePermissao> permissoes) {
		this.rolePermissao = permissoes;
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
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + nome + "]";
	}
    
    
}
