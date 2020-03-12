package com.erp.crm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.erp.auditory.listener.AuditoriaListerner;
import com.erp.auditory.model.Auditoria;
import com.erp.auditory.model.RegistroSistema;
import com.erp.config.util.ErpConfigure;

import groovy.transform.stc.ClosureParams;

@Entity
@Table(name="TAB_PESSOA",indexes={@Index(name="PESSOA_ID",columnList="PESSOA_ID"),
		                          @Index(name="PESSOA_NOME", columnList="PESSOA_NOME")})
@SequenceGenerator(name="pessoa_sequence", sequenceName="tab_pessoa_sequence", initialValue = 1, allocationSize = 1)
@Inheritance(strategy = InheritanceType.JOINED)
@SQLDelete(sql = "UPDATE TAB_PESSOA SET registro_deletado = true WHERE pessoa_id = ?")
@Where(clause=ErpConfigure.NAO_DELETADO)
@EntityListeners(AuditoriaListerner.class)
public class Pessoa implements Serializable, Auditoria{
	
	private static final long serialVersionUID = -8562517584615151910L;
	
	private Long id;
	private String nome;
	private String tipoPessoa;
	private String tipodePessoa;
	private String email;
	
	private RegistroSistema registroSistema;
	private boolean telefoneCadastrado = ErpConfigure.TELEFONE_NAO_CADASTRADO;
	private boolean enderecoCadastrado = ErpConfigure.ENDERECO_NAO_CADASTRADO;
	
	@WhereJoinTable(clause=ErpConfigure.NAO_DELETADO)
	@Where(clause=ErpConfigure.NAO_DELETADO)
	@OneToMany(mappedBy="pessoa", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Telefone> telefones;
	
	@WhereJoinTable(clause=ErpConfigure.NAO_DELETADO)
	@Where(clause=ErpConfigure.NAO_DELETADO)
	@OneToMany(mappedBy="pessoa", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Endereco> enderecos;
	
	private Endereco endereco;
	private Telefone telefone;
	
	public Pessoa() {
		endereco = new Endereco();
		telefone = new Telefone();
		telefones = new ArrayList<Telefone>();
		enderecos = new ArrayList<Endereco>();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pessoa_sequence")
    @Column(name="pessoa_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Size(min = 2, max = 100, message = "Para o nome informe de dois a até 100 caracteres ")
	@NotBlank(message="O Nome deve ser informado")
	@Column(name="PESSOA_NOME",length=100,nullable=false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Transient	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	@Transient
	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	@OneToMany(mappedBy="pessoa",cascade=CascadeType.ALL)
	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	
	@OneToMany(mappedBy="pessoa", cascade=CascadeType.ALL)
    public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	@Column(name="pessoa_tipo_pessoa",length=10,columnDefinition="VARCHAR(10)",nullable=false)
	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	
	
	@Column(name="pessoa_tipode_pessoa",length=10,columnDefinition="VARCHAR(10)",nullable=false)
	public String getTipodePessoa() {
		return tipodePessoa;
	}

	public void setTipodePessoa(String tipodePessoa) {
		this.tipodePessoa = tipodePessoa;
	}

	@NotNull(message="O E-mail deve ser informado!")
	@NotBlank(message="O E-mail deve ser informado!")
    @Column(name="pessoa_email",length=100,columnDefinition="VARCHAR(100)",nullable=true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
		
	@Column(name="pessoa_telefone_cadastrado", nullable = false)
	public boolean isTelefoneCadastrado() {
		return telefoneCadastrado;
	}
	
	public void setTelefoneCadastrado(boolean telefoneCadastrado) {
		this.telefoneCadastrado = telefoneCadastrado;
	}

	@Column(name="pessoa_endereco_cadastrado", nullable = false)
	public boolean isEnderecoCadastrado() {
		return enderecoCadastrado;
	}

	public void setEnderecoCadastrado(boolean enderecoCadastrado) {
		this.enderecoCadastrado = enderecoCadastrado;
	}
	

    @Override
    @Embedded
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
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
