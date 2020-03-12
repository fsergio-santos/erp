package com.erp.config.empresa.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.erp.auditory.listener.AuditoriaListerner;
import com.erp.auditory.model.Auditoria;
import com.erp.auditory.model.RegistroSistema;
import com.erp.config.util.ErpConfigure;

@Entity
@Table(name="TAB_MOEDA_CORRENTE", indexes={@Index(name="MOEDA_ID",columnList="MOEDA_ID")},schema="ERP")
@SequenceGenerator(name="moeda_sequence", sequenceName="tab_moeda_sequence", initialValue = 1, allocationSize = 1)
@SQLDelete(sql = "UPDATE TAB_MOEDA_CORRENTE SET registro_deletado = true WHERE moeda_id = ?")
@Where(clause=ErpConfigure.NAO_DELETADO)
@EntityListeners(AuditoriaListerner.class)
public class MoedaCorrente implements Serializable, Auditoria {
	
	private static final long serialVersionUID = 5636748645051355831L;

	private Long       id;
	private String     nome_moeda;
	private String     simbolo_moeda;
	private BigDecimal paridade_moeda;
	private RegistroSistema registroSistema;
	
	public MoedaCorrente() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="moeda_sequence")
	@Column(name="MOEDA_ID",nullable=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotBlank(message="O nome da moeda deve ser informado.")
	@NotNull(message="O nome da moeda deve ser informado.")
	@Column(name="moeda_nome", length=50, nullable=false, columnDefinition="varchar(50)")
	public String getNome_moeda() {
		return nome_moeda;
	}
	public void setNome_moeda(String nome_moeda) {
		this.nome_moeda = nome_moeda;
	}
	
	@NotBlank(message="O símbolo da moeda deve ser informado.")
	@NotNull(message="O símbolo da moeda deve ser informado.")
	@Column(name="moeda_simbolo", length=10, nullable=false, columnDefinition="varchar(10)")
	public String getSimbolo_moeda() {
		return simbolo_moeda;
	}
	public void setSimbolo_moeda(String simbolo_moeda) {
		this.simbolo_moeda = simbolo_moeda;
	}
	
	@NotBlank(message="O valor de referência da moeda deve ser informado.")
	@NotNull(message="O valor de referência da moeda deve ser informado.")
	@Column(name="moeda_paridade", length=50, nullable=false, columnDefinition="varchar(50)")
	public BigDecimal getParidade_moeda() {
		return paridade_moeda;
	}
	public void setParidade_moeda(BigDecimal paridade_moeda) {
		this.paridade_moeda = paridade_moeda;
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
		MoedaCorrente other = (MoedaCorrente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MoedaCorrente [id=" + id + ", nome_moeda=" + nome_moeda + ", simbolo_moeda=" + simbolo_moeda
				+ ", paridade_moeda=" + paridade_moeda + "]";
	}
	
	
	
	
	
}
