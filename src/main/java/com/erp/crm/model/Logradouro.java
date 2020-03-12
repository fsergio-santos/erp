package com.erp.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;

import com.erp.auditory.listener.AuditoriaListerner;
import com.erp.auditory.model.Auditoria;
import com.erp.auditory.model.RegistroSistema;
import com.erp.config.util.ErpConfigure;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name="TAB_LOGRADOURO", indexes={@Index(name="LOGRADOURO_CEP",columnList="LOGRADOURO_CEP"),
		                               @Index(name="LOGRADOURO_ID",columnList="LOGRADOURO_ID")},schema="ERP")
@SequenceGenerator(name="logradouro_sequence", sequenceName="tab_logradouro_sequence", initialValue = 1, allocationSize = 1)
@SQLDelete(sql = "UPDATE TAB_LOGRADOURO SET registro_deletado = true WHERE logradouro_id = ?")
@Where(clause=ErpConfigure.NAO_DELETADO)
@EntityListeners(AuditoriaListerner.class)
public class Logradouro implements Serializable, Auditoria {

	private Long id;
	private String cep ;
	private String tipo;
	private String descricao;
	private Cidade cidade;
	private String sigla; 
	private String complemento;
	private String descricao_sem_numero;
	private String descricao_cidade;
	private int    codigo_cidade_ibge;
	private String descricao_bairro;
	private RegistroSistema registroSistema;
	
	public Logradouro() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="logradouro_sequence")
	@Column(name="LOGRADOURO_ID",nullable=false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotBlank(message="O CEP deve ser informado")
	@Column(name="LOGRADOURO_CEP",nullable=false,length=11)
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@NotBlank(message="O Tipo de Endereço deve ser informado")
	@Column(name="LOGRADOURO_TIPO",nullable=false,length=50)
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@NotBlank(message="O Endereço deve ser informado")
	@Column(name="LOGRADOURO_DESCRICAO",nullable=false,length=100)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	@WhereJoinTable(clause=ErpConfigure.NAO_DELETADO)
	@Where(clause=ErpConfigure.NAO_DELETADO)
	@JsonIgnoreProperties("logradouros")
	@NotBlank(message="O Município deve ser informado")
	@ManyToOne(targetEntity=Cidade.class,fetch=FetchType.LAZY)
	@JoinColumn(nullable=false, name="CIDADE_ID",foreignKey=@ForeignKey(name="FK_LOGRADOURO_CIDADE"),columnDefinition="BIGINT(20)")
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	@NotBlank(message="A Sigla do estado deve ser informada")
	@Column(name="ESTADO_SIGLA",nullable=false,length=2)
	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@NotBlank(message="O Complemento do Endereço deve ser informado")
	@Column(name="LOGRADOURO_COMPLEMENTO",nullable=false,length=100)
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
    
	
	@Column(name="LOGRADOURO_DESCRICAO_SEM_NUMERO",nullable=false,length=100)
	public String getDescricao_sem_numero() {
		return descricao_sem_numero;
	}

	public void setDescricao_sem_numero(String descricao_sem_numero) {
		this.descricao_sem_numero = descricao_sem_numero;
	}

	@NotBlank(message="A Descrição da cidade - Nome deve ser informado")
	@Column(name="LOGRADOURO_CIDADE_DESCRICAO",nullable=false,length=100)
	public String getDescricao_cidade() {
		return descricao_cidade;
	}

	public void setDescricao_cidade(String descricao_cidade) {
		this.descricao_cidade = descricao_cidade;
	}

	@NotBlank(message="O Código do IBGE deve ser informado")
	@Column(name="LOGRADOURO_CIDADE_IBGE",nullable=false)
	public int getCodigo_cidade_ibge() {
		return codigo_cidade_ibge;
	}

	public void setCodigo_cidade_ibge(int codigo_cidade_ibge) {
		this.codigo_cidade_ibge = codigo_cidade_ibge;
	}

	@NotBlank(message="O Nome do Bairro deve ser informado")
	@Column(name="LOGRADOURO_BAIRRO",nullable=true,length=100)
	public String getDescricao_bairro() {
		return descricao_bairro;
	}

	public void setDescricao_bairro(String descricao_bairro) {
		this.descricao_bairro = descricao_bairro;
	}
	
	@Embedded
	@Override
	public RegistroSistema getRegistroSistema() {
		return registroSistema;
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
		Logradouro other = (Logradouro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Logradouro [id=" + id + ", cep=" + cep + ", tipo=" + tipo + ", descricao=" + descricao + ", cidade="
				+ cidade + ", sigla=" + sigla + ", complemento=" + complemento + ", descricao_sem_numero="
				+ descricao_sem_numero + ", descricao_cidade=" + descricao_cidade + ", codigo_cidade_ibge="
				+ codigo_cidade_ibge + ", descricao_bairro=" + descricao_bairro + "]";
	}
	
	
	
}
