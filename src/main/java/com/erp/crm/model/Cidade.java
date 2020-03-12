package com.erp.crm.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name="TAB_CIDADE",indexes={@Index(name="CIDADE_ID",columnList="CIDADE_ID"),
								  @Index(name="CIDADE_NOME", columnList="CIDADE_NOME")},schema="ERP")
@SequenceGenerator(name="cidade_sequence",sequenceName="tab_cidade_sequence", initialValue = 1, allocationSize = 1)
@SQLDelete(sql = "UPDATE TAB_CIDADE SET registro_deletado = true WHERE cidade_id = ?")
@Where(clause=ErpConfigure.NAO_DELETADO)
@EntityListeners(AuditoriaListerner.class)
public class Cidade implements Serializable, Auditoria{

	private static final long serialVersionUID = -9139153435200588906L;
	
    private Long Id;
    private String nome;
    private String sigla;
    private int    ibge;
    private String ddd;
    private Estado estado;
    private RegistroSistema registroSistema;
    private List<Logradouro> logradouros;

    public Cidade() {
		super();
	}
    
    public Cidade(Long id, String nome, String sigla, int ibge, String ddd) {
		super();
		Id = id;
		this.nome = nome;
		this.sigla = sigla;
		this.ibge = ibge;
		this.ddd = ddd;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cidade_sequence")
    @Column(name="CIDADE_ID",nullable=false)
	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}

	@NotBlank(message="O nome da cidade deve ser informado.")
	@NotNull(message="O nome da cidade deve ser informado.")
    @Column(name="cidade_nome",length=100, nullable=false, columnDefinition="VARCHAR(100)")
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	

	@NotBlank(message="A sigla do estado deve ser informado.")
	@NotNull(message="A sigla do estado deve ser informado.")
	@Column(name="estado_sigla",length=2, nullable=false,columnDefinition="VARCHAR(2)")
	public String getSigla() {
		return sigla;
	}


	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@Column(name="cidade_ibge", nullable=false)
	public int getIbge() {
		return ibge;
	}


	public void setIbge(int ibge) {
		this.ibge = ibge;
	}

	@Column(name="cidade_ddd",length=4, nullable=false)
	public String getDdd() {
		return ddd;
	}

	
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	

	@Where(clause=ErpConfigure.NAO_DELETADO)
	@JsonIgnoreProperties("cidade")
	@OneToMany(mappedBy="cidade",fetch=FetchType.LAZY)
	public List<Logradouro> getLogradouros() {
		return logradouros;
	}


	public void setLogradouros(List<Logradouro> logradouros) {
		this.logradouros = logradouros;
	}
	

	@WhereJoinTable(clause=ErpConfigure.NAO_DELETADO)
	@Where(clause=ErpConfigure.NAO_DELETADO)
	@ManyToOne(targetEntity=Estado.class,fetch=FetchType.LAZY)
	@JoinColumn(nullable=false, name="ESTADO_ID",foreignKey=@ForeignKey(name="FK_ESTADO_CIDADE"),columnDefinition="BIGINT(20)")
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
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
		Cidade other = (Cidade) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Cidade [Id=" + Id + ", nome=" + nome + ",  sigla=" + sigla + ", ibge=" + ibge
				+ ", ddd=" + ddd + "]";
	}
    
    
}

