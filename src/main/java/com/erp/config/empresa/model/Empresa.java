package com.erp.config.empresa.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;

import com.erp.auditory.listener.AuditoriaListerner;
import com.erp.auditory.model.Auditoria;
import com.erp.auditory.model.RegistroSistema;
import com.erp.config.util.ErpConfigure;
import com.erp.crm.model.Cidade;
import com.erp.crm.model.Estado;
import com.erp.crm.model.Logradouro;
import com.erp.crm.model.Pais;


@Entity
@Table(name="TAB_EMPRESA",indexes={@Index(name="EMPRESA_ID",columnList="EMPRESA_ID")},schema="ERP")
@SequenceGenerator(name="empresa_sequence", sequenceName="tab_empresa_sequence", initialValue = 1, allocationSize = 1)
@SQLDelete(sql = "UPDATE TAB_EMPRESA SET registro_deletado = true WHERE empresa_id = ?")
@Where(clause=ErpConfigure.NAO_DELETADO)
@EntityListeners(AuditoriaListerner.class)
public class Empresa implements Serializable, Auditoria {

	private static final long serialVersionUID = 2191030004150194248L;

	private long     id;
	private String   nome;
	private String   nome_fantasia;
	private Logradouro endereco;
	private Pais     pais;
	private Cidade   cidade;
	private Estado   estado;
	private MoedaCorrente moeda;
	private String   porteEmpresa;
	private String   inscricao_estadual;
	private String   cnpj;
	private String   inscricao_municipal;
	private RegistroSistema registroSistema;
	
	public Empresa() {
	
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="empresa_sequence")
	@Column(name="EMPRESA_ID",nullable=false)
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

    @NotBlank(message="O nome da empresa deve ser informado.")
	@NotNull(message="O nome da empresa deve ser informado.")
	@Column(name="empresa_nome", length=100, nullable=false, columnDefinition="varchar(100)")
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotBlank(message="O nome fantasia da empresa deve ser informado.")
	@NotNull(message="O nome fantasia da empresa deve ser informado.")
	@Column(name="empresa_nome_fantasia", length=100, nullable=false, columnDefinition="varchar(100)")
	public String getNome_fantasia() {
		return nome_fantasia;
	}


	public void setNome_fantasia(String nome_fantasia) {
		this.nome_fantasia = nome_fantasia;
	}

	@WhereJoinTable(clause=ErpConfigure.NAO_DELETADO)
	@Where(clause=ErpConfigure.NAO_DELETADO)
	@OneToOne
	@JoinColumn(name = "logradouro_id", referencedColumnName = "logradouro_id",nullable=false,columnDefinition="BIGINT(20)")
	public Logradouro getEndereco() {
		return endereco;
	}


	public void setEndereco(Logradouro endereco) {
		this.endereco = endereco;
	}

	@WhereJoinTable(clause=ErpConfigure.NAO_DELETADO)
	@Where(clause=ErpConfigure.NAO_DELETADO)
    @OneToOne
    @JoinColumn(name = "pais_id", referencedColumnName = "pais_id",nullable=false,columnDefinition="BIGINT(20)")
	public Pais getPais() {
		return pais;
	}


	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@WhereJoinTable(clause=ErpConfigure.NAO_DELETADO)
	@Where(clause=ErpConfigure.NAO_DELETADO)
    @OneToOne 
    @JoinColumn(name = "cidade_id", referencedColumnName = "cidade_id",nullable=false,columnDefinition="BIGINT(20)",foreignKey=@ForeignKey(name="FK_EMPRESA_CIDADE"))
	public Cidade getCidade() {
		return cidade;
	}


	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	@NotBlank(message="O porte da empresa deve ser informado.")
	@NotNull(message="O porte da empresa deve ser informado.")
	@Column(name="empresa_porte", length=20, nullable=false, columnDefinition="varchar(20)")
	public String getPorteEmpresa() {
		return porteEmpresa;
	}


	public void setPorteEmpresa(String porteEmpresa) {
		this.porteEmpresa = porteEmpresa;
	}

	@NotBlank(message="A inscrição estadual da empresa deve ser informado.")
	@NotNull(message="A inscrição estadual da empresa deve ser informado.")
	@Column(name="empresa_inscricao_estadual", length=20, nullable=false, columnDefinition="varchar(20)")
	public String getInscricao_estadual() {
		return inscricao_estadual;
	}


	public void setInscricao_estadual(String inscricao_estadual) {
		this.inscricao_estadual = inscricao_estadual;
	}

	@NotBlank(message="O cnpj da empresa deve ser informado.")
	@NotNull(message="O cnpj da empresa deve ser informado.")
	@Column(name="empresa_cnpj", length=20, nullable=false, columnDefinition="varchar(20)")
	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@NotBlank(message="A inscrição minicipal da empresa deve ser informado.")
	@NotNull(message="A inscrição minicipal da empresa deve ser informado.")
	@Column(name="empresa_inscricao_municipal", length=20, nullable=false, columnDefinition="varchar(20)")
	public String getInscricao_municipal() {
		return inscricao_municipal;
	}


	public void setInscricao_municipal(String inscricao_municipal) {
		this.inscricao_municipal = inscricao_municipal;
	}

	@WhereJoinTable(clause=ErpConfigure.NAO_DELETADO)
	@Where(clause=ErpConfigure.NAO_DELETADO)
	@OneToOne
	@JoinColumn(name = "moeda_id", referencedColumnName = "moeda_id",nullable=false,columnDefinition="BIGINT(20)",foreignKey=@ForeignKey(name="FK_EMPRESA_MOEDA"))
	public MoedaCorrente getMoeda() {
		return moeda;
	}

	public void setMoeda(MoedaCorrente moeda) {
		this.moeda = moeda;
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
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Empresa other = (Empresa) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", nome=" + nome + ", nome_fantasia=" + nome_fantasia + ", endereco=" + endereco
				+ ", pais=" + pais + ", cidade=" + cidade + ", estado=" + estado + ", moeda=" + moeda
				+ ", porteEmpresa=" + porteEmpresa + ", inscricao_estadual=" + inscricao_estadual + ", cnpj=" + cnpj
				+ ", inscricao_municipal=" + inscricao_municipal + "]";
	}
	
	
	
	
}
