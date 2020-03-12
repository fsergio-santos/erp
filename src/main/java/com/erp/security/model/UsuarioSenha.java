package com.erp.security.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;

import com.erp.auditory.listener.AuditoriaListerner;
import com.erp.auditory.model.Auditoria;
import com.erp.auditory.model.RegistroSistema;
import com.erp.config.util.ErpConfigure;


@Entity
@Table(name="TAB_USUARIO_SENHA")
@SequenceGenerator(name="usuario_senha_sequence",sequenceName="tab_usuario_sequence", initialValue = 1, allocationSize = 1)
@SQLDelete(sql = "UPDATE TAB_USUARIO_SENHA SET registro_deletado = true WHERE usuario_senha_id = ?")
@Where(clause=ErpConfigure.NAO_DELETADO)
@EntityListeners(AuditoriaListerner.class)
public class UsuarioSenha implements Serializable, Auditoria{


	private Long Id;
	private Usuario usuario;
	private String  senha;
	private RegistroSistema registroSistema;
	
	public UsuarioSenha() {
	}

	public UsuarioSenha(Long id, Usuario usuario, String senha) {
		super();
		Id = id;
		this.usuario = usuario;
		this.senha = senha;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="usuario_senha_sequence")
	@Column(name="usuario_senha_id")
	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}

	@Column(name="usuario_senha",nullable=false,length=100)
	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	@WhereJoinTable(clause=ErpConfigure.NAO_DELETADO)
	@Where(clause=ErpConfigure.NAO_DELETADO)
	@ManyToOne(targetEntity=Usuario.class,fetch=FetchType.LAZY)
	@JoinColumn(name="usuario_id",nullable=false)
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
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
		UsuarioSenha other = (UsuarioSenha) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsuarioSenha [Id=" + Id + ", senha=" + senha + "]";
	}

	

}
