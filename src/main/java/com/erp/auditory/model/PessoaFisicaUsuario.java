package com.erp.auditory.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


import com.erp.config.util.ErpConfigure;
import com.erp.crm.model.PessoaFisica;
import com.erp.security.model.Usuario;


@Entity
@Table(name="TAB_PESSOAFISICA_USUARIO",indexes={@Index(name="PESSOAFISICA_USUARIO_ID",columnList="PESSOAFISICA_USUARIO_ID")},schema="ERP")
@SequenceGenerator(name="pessoaFisica_usuario_sequence", sequenceName="tab_pessoaFisica_usuario_sequence", initialValue = 1, allocationSize = 1)
public class PessoaFisicaUsuario implements Serializable {

	private Long    id;
	private PessoaFisica  pessoaFisica;
	private Usuario usuario;
	private Date    dataOperacao;
	private String  tipoOperacao;

		
	public PessoaFisicaUsuario() {
		super();
	}


	public PessoaFisicaUsuario(PessoaFisica pessoaFisica, Usuario usuario, Date dataOperacao, String tipoOperacao) {
		super();
		this.pessoaFisica = pessoaFisica;
		this.usuario = usuario;
		this.dataOperacao = dataOperacao;
		this.tipoOperacao = tipoOperacao;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pessoaFisica_usuario_sequence")
	@Column(name="PESSOAFISICA_USUARIO_ID",nullable=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="pessoaFisica_id")
	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}
	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}
	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@DateTimeFormat(iso = ISO.DATE, pattern = "")
	@Temporal(TemporalType.DATE)
	@Column(name="data_operacao", nullable=true)
	public Date getDataOperacao() {
		return dataOperacao;
	}
	public void setDataOperacao(Date dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	
	@Column(name="tipo_operacao", nullable=true)
	public String getTipoOperacao() {
		return tipoOperacao;
	}
	
	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
	
	
	@PrePersist
	@PreUpdate
	public void onCreate() {
		this.setDataOperacao(new Date());
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
		PessoaFisicaUsuario other = (PessoaFisicaUsuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
