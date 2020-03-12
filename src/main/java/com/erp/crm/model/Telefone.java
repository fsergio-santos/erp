package com.erp.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
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
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;

import com.erp.auditory.listener.AuditoriaListerner;
import com.erp.auditory.model.Auditoria;
import com.erp.auditory.model.RegistroSistema;
import com.erp.config.util.ErpConfigure;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.ForeignKey;

@Entity
@Table(name="TAB_TELEFONE",indexes={@Index(name="TELEFONE_ID",columnList="TELEFONE_ID")},schema="ERP")
@SequenceGenerator(name="telefone_sequence", sequenceName="tab_telefone_sequence", initialValue = 1, allocationSize = 1)
@SQLDelete(sql = "UPDATE TAB_TELEFONE SET registro_deletado = true WHERE telefone_id = ?")
@Where(clause=ErpConfigure.NAO_DELETADO)
@EntityListeners(AuditoriaListerner.class)
public class Telefone implements Serializable, Auditoria{
	
	private Long          id;
	private String        tipoTelefone;
	private String        numeroTelefone;
    private String        modeloTelefone;
	private Pessoa        pessoa;
	
	private RegistroSistema registroSistema;
	
	public Telefone() {
		super();
	}


	public Telefone(Long id, String tipoTelefone, String numeroTelefone) {
		super();
		this.id = id;
		this.tipoTelefone = tipoTelefone;
		this.numeroTelefone = numeroTelefone;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="telefone_sequence")
	@Column(name="telefone_id")
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	@NotBlank(message="O tipo do telefone deve ser informado.")
	@NotNull(message="O tipo do telefone deve ser informado.")
    @Column(name="telefone_tipo",length=20,nullable=false)
	public String getTipoTelefone() {
		return tipoTelefone;
	}


	public void setTipoTelefone(String tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	@NotBlank(message="O número do telefone deve ser informado.")
	@NotNull(message="O número do telefone deve ser informado.")
    @Column(name="telefone_numero",length=20,nullable=false)
	public String getNumeroTelefone() {
		return numeroTelefone;
	}


	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}
	
	
	@NotBlank(message="O modelo do telefone deve ser informado.")
	@NotNull(message="O modelo do telefone deve ser informado.")
    @Column(name="telefone_modelo",length=20,nullable=false)
	public String getModeloTelefone() {
		return modeloTelefone;
	}


	public void setModeloTelefone(String modeloTelefone) {
		this.modeloTelefone = modeloTelefone;
	}


	@WhereJoinTable(clause=ErpConfigure.NAO_DELETADO)
	@Where(clause=ErpConfigure.NAO_DELETADO)
	@JsonIgnoreProperties({"telefone","endereco","telefones","enderecos"})
	@ManyToOne(targetEntity=Pessoa.class,fetch=FetchType.LAZY)
    @JoinColumn(nullable=false, name="pessoa_id",foreignKey=@ForeignKey(name="FK_TELEFONE_PESSOA"))
	public Pessoa getPessoa() {
		return pessoa;
	}


	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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
		Telefone other = (Telefone) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Telefone [id=" + id + ", tipoTelefone=" + tipoTelefone + ", numeroTelefone=" + numeroTelefone + "]";
	}
	
	

}
