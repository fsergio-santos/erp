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
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;

import com.erp.auditory.listener.AuditoriaListerner;
import com.erp.auditory.model.Auditoria;
import com.erp.auditory.model.RegistroSistema;
import com.erp.config.util.ErpConfigure;
import com.erp.config.util.anotations.ExisteNome;
import com.erp.security.service.PermissaoService;

@Entity
@Table(name="TAB_PERMISSAO",indexes={@Index(name="PERMISSAO_ID",columnList="PERMISSAO_ID"),@Index(name="PERMISSAO_NOME",columnList="PERMISSAO_NOME")},schema="FINANCEIRO")
@SequenceGenerator(name="permissao_sequence",sequenceName="tab_permissao_sequence", initialValue = 1, allocationSize = 1)
@SQLDelete(sql = "UPDATE TAB_PERMISSAO SET registro_deletado = true WHERE permissao_id = ?")
@Where(clause=ErpConfigure.NAO_DELETADO)
@EntityListeners(AuditoriaListerner.class)
public class Permissao  implements Serializable, Auditoria{

	private Long id;
	private String nome;
	private boolean checked = false;
	private List<RolePermissao> rolePermissao;
	private RegistroSistema registroSistema;
	
	
	public Permissao() {
	}
			
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="permissao_sequence")
	@Column(name="permissao_id")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	///@ExisteNome(service=PermissaoService.class,fieldName="nome")
	@NotNull( message = "O nome da Permissão é obrigatório.")
	@NotBlank(message="O nome da Permissão é obrigatório.")
	@Column(name="permissao_nome",length=50, nullable=false)
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	

	@Where(clause=ErpConfigure.NAO_DELETADO)
	@OneToMany(mappedBy="permissaoId",fetch=FetchType.EAGER)
	public List<RolePermissao> getRolePermissao() {
		return rolePermissao;
	}

	public void setRolePermissao(List<RolePermissao> rolePermissao) {
		this.rolePermissao = rolePermissao;
	}
	
	
    @Transient
	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
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
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Permissao other = (Permissao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Permissao [id=" + id + ", nome=" + nome + "]";
	}
	
	
	

	
}
