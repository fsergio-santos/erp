package com.erp.crm.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.erp.auditory.listener.AuditoriaListerner;
import com.erp.auditory.model.Auditoria;
import com.erp.auditory.model.RegistroSistema;
import com.erp.config.empresa.model.enumerate.PorteEmpresa;
import com.erp.config.util.ErpConfigure;




@Entity
@Table(name="TAB_PESSOA_JURIDICA",indexes={@Index(name="PESSOA_CNPJ",columnList="PESSOA_CNPJ"),
		                                   @Index(name="PESSOA_NOME_FANTASIA", columnList="PESSOA_NOME_FANTASIA")})
@PrimaryKeyJoinColumn(name = "pessoa_juridica_id")
@SQLDelete(sql = "UPDATE TAB_PESSOA_JURIDICA SET registro_deletado = true WHERE pessoa_juridica_id = ?")
@EntityListeners(AuditoriaListerner.class)
public class PessoaJuridica extends Pessoa implements Serializable, Auditoria {
	
	private String cnpj; 
	private String nomeFantasia;
	private String porteEmpresa;
	private Date dataAbertura;
	private String inscricaoEstadual;
	private String inscricaoMunicipal;
	private String naturezaJuridica;
	private String ramoNegocio;
	private String tipoPessoaJuridica;
	private String formaPessoaJuridica;
	private RegistroSistema registroSistema;
	
	private Date dataInsert;
	private Date dataUpdate;
	private Date dataDelete;
	private boolean deleted = false;
	
	
	public PessoaJuridica() {
		super();
	}

	
	@CNPJ
	@NotBlank(message="O CNPJ deve ser informado")
	@Column(name="pessoa_cnpj", length=20, nullable=false)
	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	
	@Size(min = 2, max = 100, message = "Para o nome fantasia informe de dois a até 100 caracteres ")
	@NotBlank(message="O nome fantasia deve ser informado")
	@Column(name="pessoa_nome_fantasia", length=100, nullable=false)
	public String getNomeFantasia() {
		return nomeFantasia;
	}


	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	@Size(min = 1,  message = "Selecione uma caracteristica da empresa ")
	@Column(name="pessoa_porte_empresa", length=20, nullable=false)
	public String getPorteEmpresa() {
		return porteEmpresa;
	}


	public void setPorteEmpresa(String porteEmpresa) {
		this.porteEmpresa = porteEmpresa;
	}

	@DateTimeFormat(iso = ISO.DATE, pattern = "")
	@Temporal(TemporalType.DATE)
	@NotNull(message="A data de abertura deve ser informada")
	@Column(name = "pessoa_data_abertura",nullable=false)
	public Date getDataAbertura() {
		return dataAbertura;
	}


	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	
	@NotBlank(message="O nome fantasia deve ser informado")
	@Column(name="pessoa_inscricao_estadual", length=30, nullable=false)
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}


	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	
	@NotBlank(message="A Inscrição Municipal deve ser informado")
	@Column(name="pessoa_inscricao_municipal", length=30, nullable=false)
	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}


	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	@NotBlank(message="A Natureza Juridica deve ser informado")
	@Column(name="pessoa_natureza_juridica", length=30, nullable=false)
	public String getNaturezaJuridica() {
		return naturezaJuridica;
	}


	public void setNaturezaJuridica(String naturezaJuridica) {
		this.naturezaJuridica = naturezaJuridica;
	}
    
	public String getTipoPessoaJuridica() {
		return tipoPessoaJuridica;
	}

	public void setTipoPessoaJuridica(String tipoPessoaJuridica) {
		this.tipoPessoaJuridica = tipoPessoaJuridica;
	}

	@NotBlank(message="O Ramo de Negócio deve ser informado")
	@Column(name="pessoa_ramo_negocio", length=100, nullable=false)
	public String getRamoNegocio() {
		return ramoNegocio;
	}

	public void setRamoNegocio(String ramoNegocio) {
		this.ramoNegocio = ramoNegocio;
	}
	
	
	@NotBlank(message="Forma de Constituição da Empresa deve ser informado")
	@Column(name="pessoa_forma_juridica", length=100, nullable=false)
	public String getFormaPessoaJuridica() {
		return formaPessoaJuridica;
	}


	public void setFormaPessoaJuridica(String formaPessoaJuridica) {
		this.formaPessoaJuridica = formaPessoaJuridica;
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
		
	
	
}
