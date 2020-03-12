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
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;

import com.erp.auditory.listener.AuditoriaListerner;
import com.erp.auditory.model.Auditoria;
import com.erp.auditory.model.RegistroSistema;
import com.erp.config.util.ErpConfigure;


@Entity
@Table(name="TAB_ESTADO",indexes={@Index(name="ESTADO_ID",columnList="ESTADO_ID"),
  						    	  @Index(name="ESTADO_NOME", columnList="ESTADO_NOME")},schema="ERP")
@SequenceGenerator(name="estado_sequence", sequenceName="tab_estado_sequence", initialValue = 1, allocationSize = 1)
@SQLDelete(sql = "UPDATE TAB_ESTADO SET registro_deletado = true WHERE estado_id = ?")
@Where(clause=ErpConfigure.NAO_DELETADO)
@EntityListeners(AuditoriaListerner.class)
public class Estado implements Serializable, Auditoria {

	private static final long serialVersionUID = -3153995193658868569L;
	
	private Long id;
	private String  nome;
	private String  sigla;
	private Pais pais;
	private RegistroSistema registroSistema;
	
	
	public Estado() {
		
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="estado_sequence")
	@Column(name="ESTADO_ID",nullable=false)
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	@NotBlank(message="O Nome do Estado tem que ser informado.")
    @Column(name="estado_nome", length=100, nullable=false)
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotBlank(message="A Sigla do Estado tem que ser informada")
	@Column(name="estado_sigla", length=2, nullable=false)
	public String getSigla() {
		return sigla;
	}


	public void setSigla(String sigla) {
		this.sigla = sigla;
	}


	@WhereJoinTable(clause=ErpConfigure.NAO_DELETADO)
	@Where(clause=ErpConfigure.NAO_DELETADO)
    @ManyToOne(fetch=FetchType.LAZY,targetEntity=Pais.class)
    @JoinColumn(name="pais_id",nullable=false, foreignKey=@ForeignKey(name="FK_PAIS_ESTADO"),columnDefinition="BIGINT(20)")
	public Pais getPais() {
		return pais;
	}


	public void setPais(Pais pais) {
		this.pais = pais;
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
		Estado other = (Estado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Estado [id=" + id + ", nome=" + nome + ", sigla=" + sigla + "]";
	}

	
	
}
