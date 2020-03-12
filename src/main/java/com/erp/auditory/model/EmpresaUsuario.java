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

import com.erp.config.empresa.model.Empresa;
import com.erp.config.util.ErpConfigure;
import com.erp.security.model.Usuario;


@Entity
@Table(name="TAB_EMPRESA_USUARIO",indexes={@Index(name="EMPRESA_USUARIO_ID",columnList="EMPRESA_USUARIO_ID")},schema="ERP")
@SequenceGenerator(name="empresa_usuario_sequence", sequenceName="tab_empresa_usuario_sequence", initialValue = 1, allocationSize = 1)
public class EmpresaUsuario implements Serializable {

	private Long    id;
	private Empresa empresa;
	private Usuario usuario;
	private Date    dataOperacao;
	private String  tipoOperacao;

		
	public EmpresaUsuario() {
		super();
	}


	public EmpresaUsuario(Empresa empresa, Usuario usuario, Date dataOperacao, String tipoOperacao) {
		super();
		this.empresa = empresa;
		this.usuario = usuario;
		this.dataOperacao = dataOperacao;
		this.tipoOperacao = tipoOperacao;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="empresa_usuario_sequence")
	@Column(name="EMPRESA_USUARIO_ID",nullable=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="empresa_id")
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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
		EmpresaUsuario other = (EmpresaUsuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
