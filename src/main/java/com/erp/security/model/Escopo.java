package com.erp.security.model;

import java.io.Serializable;

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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.erp.auditory.listener.AuditoriaListerner;
import com.erp.auditory.model.Auditoria;
import com.erp.auditory.model.RegistroSistema;
import com.erp.config.util.ErpConfigure;

@Entity
@Table(name="TAB_ESCOPO", indexes={@Index(name="ESCOPO_ID",columnList="ESCOPO_ID")})
@SequenceGenerator(name="escopo_sequence",sequenceName="tab_escopo_sequence", initialValue = 1, allocationSize = 1)
@SQLDelete(sql = "UPDATE TAB_ESCOPO SET registro_deletado = true WHERE escopo_id = ?")
@Where(clause=ErpConfigure.NAO_DELETADO)
@EntityListeners(AuditoriaListerner.class)
public class Escopo implements Serializable, Auditoria{

	private Long id;
	private String nome;
	private RegistroSistema registroSistema;
	
	public Escopo() {
	}
	
	public Escopo(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="escopo_sequence")
	@Column(name="escopo_id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotBlank
	@NotNull
	@Column(name="escopo_nome",length=15,nullable=false)
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
		Escopo other = (Escopo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Escopo [id=" + id + ", nome=" + nome + "]";
	}

	
	
	

}
