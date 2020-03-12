package com.erp.rh.model;

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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.erp.auditory.listener.AuditoriaListerner;
import com.erp.auditory.model.Auditoria;
import com.erp.auditory.model.RegistroSistema;
import com.erp.config.util.ErpConfigure;
import com.erp.crm.model.Estado;

@Entity
@Table(name="TAB_FUNCAO",indexes={@Index(name="FUNCAO_ID",columnList="FUNCAO_ID")},schema="ERP")
@SequenceGenerator(name="funcao_sequence", sequenceName="tab_funcao_sequence", initialValue = 1, allocationSize = 1)
@SQLDelete(sql = "UPDATE TAB_FUNCAO SET registro_deletado = true WHERE funcao_id = ?")
@Where(clause=ErpConfigure.NAO_DELETADO)
@EntityListeners(AuditoriaListerner.class)
public class Funcao implements Serializable, Auditoria{
	
	private Long id;
	private String nome;
	private RegistroSistema registroSistema;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="funcao_sequence")
	@Column(name="FUNCAO_ID")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotBlank(message="O nome da função deve ser informado.")
	@NotNull(message="O nome da função deve ser informado.")
    @Column(name="funcao_nome",columnDefinition="VARCHAR(20)",length=20,nullable=false)
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
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
		Funcao other = (Funcao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Funcao [id=" + id + ", nome=" + nome + "]";
	}
	
   
}
