package com.erp.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;
import org.springframework.http.ResponseEntity;

import com.erp.auditory.listener.AuditoriaListerner;
import com.erp.auditory.model.Auditoria;
import com.erp.auditory.model.RegistroSistema;
import com.erp.config.util.ErpConfigure;
import com.erp.crm.model.enumerate.TipoEndereco;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

@Entity
@Table(name="TAB_ENDERECO",indexes={@Index(name="ENDERECO_ID",columnList="ENDERECO_ID")})
@SequenceGenerator(name="endereco_sequence",sequenceName="tab_endereco_sequence", initialValue = 1, allocationSize = 1)
@SQLDelete(sql = "UPDATE TAB_ENDERECO SET registro_deletado = true WHERE endereco_id = ?")
@Where(clause=ErpConfigure.NAO_DELETADO)
@EntityListeners(AuditoriaListerner.class)
public class Endereco implements Serializable, Auditoria {
	
	private static final long serialVersionUID = -7865730032364631741L;

	private Long id;
	private Cidade cidade;
	private Logradouro logradouro;
	private String numero;
	private TipoEndereco tipoEndereco;
	private Pessoa pessoa;
	private RegistroSistema registroSistema;
	
    private String cep ;
	private String tipo;
	private String descricao;
	private String complemento;
	private String descricao_sem_numero;
	private String descricao_bairro;
	
	public Endereco() {
	    logradouro = new Logradouro();
	    cidade = new Cidade();
	}
	

    @Id	
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="endereco_sequence")
    @Column(name="ENDERECO_ID", nullable=false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	@WhereJoinTable(clause=ErpConfigure.NAO_DELETADO)
	@Where(clause=ErpConfigure.NAO_DELETADO)
	@JsonIgnoreProperties({"logradouros","estado"})
    @NotNull(message="O Nome da Cidade deve ser informado")
    @ManyToOne(targetEntity=Cidade.class,fetch=FetchType.LAZY)
    @JoinColumn(nullable=false,name="CIDADE_ID",foreignKey=@ForeignKey(name="FK_PESSOA_CIDADE"),referencedColumnName="CIDADE_ID")
    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    

	@WhereJoinTable(clause=ErpConfigure.NAO_DELETADO)
	@Where(clause=ErpConfigure.NAO_DELETADO)
    @JsonIgnoreProperties("cidade")
    @NotNull(message="O logradouro deve ser informado")
    @ManyToOne(targetEntity=Logradouro.class,fetch=FetchType.LAZY)
    @JoinColumn(nullable=false,name="LOGRADOURO_ID",columnDefinition="BIGINT(20)",foreignKey=@ForeignKey(name="FK_PESSOA_LOGRADOURO"))
    public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}


	@WhereJoinTable(clause=ErpConfigure.NAO_DELETADO)
	@Where(clause=ErpConfigure.NAO_DELETADO)
	@JsonIgnoreProperties({"telefones","enderecos","pessoaFisica","pessoaJuridica","telefone","endereco"})
	@ManyToOne(targetEntity=Pessoa.class,fetch=FetchType.LAZY)
    @JoinColumn(nullable=false,name="PESSOA_ID",columnDefinition="BIGINT(20)",foreignKey=@ForeignKey(name="FK_PESSOA_ENDERECO"))
	public Pessoa getPessoa() {
		return pessoa;
	}


	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@NotNull(message="O Número deve ser informado")
	@Column(name="LOGRADOURO_NUMERO", length=6, nullable=false)
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	

	@Enumerated(EnumType.STRING) 
	@Column(name="LOGRADOURO_TIPO_ENDERECO", length=20, nullable=false)
    public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}
	
	@Transient
	@JsonIgnore
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Transient
	@JsonIgnore
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Transient
	@JsonIgnore
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Transient
	@JsonIgnore
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@Transient
	@JsonIgnore
	public String getDescricao_sem_numero() {
		return descricao_sem_numero;
	}

	public void setDescricao_sem_numero(String descricao_sem_numero) {
		this.descricao_sem_numero = descricao_sem_numero;
	}

	@Transient
	@JsonIgnore
	public String getDescricao_bairro() {
		return descricao_bairro;
	}

	public void setDescricao_bairro(String descricao_bairro) {
		this.descricao_bairro = descricao_bairro;
	}
	
	@PostLoad
	public void updateFields() {
		setCep(this.getLogradouro().getCep());
		setTipo(this.getLogradouro().getTipo());
		setDescricao(this.getLogradouro().getDescricao());
		setComplemento(this.getLogradouro().getComplemento());
		setDescricao_sem_numero(this.getLogradouro().getDescricao_sem_numero());
		setDescricao_bairro(this.getLogradouro().getDescricao_bairro());
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
}
