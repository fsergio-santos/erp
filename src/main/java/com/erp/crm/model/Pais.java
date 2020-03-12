package com.erp.crm.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="TAB_PAIS",indexes={@Index(name="PAIS_ID",columnList="PAIS_ID")},schema="ERP")
@SequenceGenerator(name="pais_sequence",sequenceName="tab_pais_sequence", initialValue = 1, allocationSize = 1)
@SQLDelete(sql = "UPDATE TAB_PAIS SET registro_deletado = true WHERE pais_id = ?")
@Where(clause=ErpConfigure.NAO_DELETADO)
@EntityListeners(AuditoriaListerner.class)
public class Pais implements Serializable, Auditoria{

	private static final long serialVersionUID = 8214139111490267678L;

	private Long id;
	private String nome;
	private String nacionalidade;
	private RegistroSistema registroSistema;
	private List<Estado> estados;
	
	public Pais() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pais_sequence")
	@Column(name="PAIS_ID")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotBlank(message="O nome do pais deve ser informado.")
	@NotNull(message="O nome do pais deve ser informado.")
	@Column(name="pais_nome", length=50, nullable=false, columnDefinition="varchar(50)")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	
	@Where(clause=ErpConfigure.NAO_DELETADO)
	@JsonIgnoreProperties("pais")
	@OneToMany(mappedBy="pais")
	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	
	
	@Column(name="pais_nacionalidade", length=50, nullable=false, columnDefinition="varchar(50)")
	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
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
		Pais other = (Pais) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pais [id=" + id + ", nome=" + nome + "]";
	}
	
	
	
	
	
	
			
}
