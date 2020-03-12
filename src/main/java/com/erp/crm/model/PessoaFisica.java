package com.erp.crm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ForeignKey;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.erp.auditory.listener.AuditoriaListerner;
import com.erp.auditory.model.Auditoria;
import com.erp.auditory.model.RegistroSistema;
import com.erp.config.util.ErpConfigure;
import com.erp.crm.model.enumerate.EstadoCivil;
import com.erp.crm.model.enumerate.Sexo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="TAB_PESSOA_FISICA",indexes={@Index(name="PESSOA_CPF",columnList="PESSOA_CPF")})
@PrimaryKeyJoinColumn(name = "pessoa_fisica_id")
@SQLDelete(sql = "UPDATE TAB_PESSOA_FISICA SET registro_deletado = true WHERE pessoa_fisica_id = ?")
@EntityListeners(AuditoriaListerner.class)
public class PessoaFisica extends Pessoa implements Serializable, Auditoria {

	private static final long serialVersionUID = -8655854042431893930L;

	private String cpf;
	private Date dataNascimento;
	private String rg;
	private String emissorRg;
	private Date dataEmissaoRg;
	private String nomeMae;
	private String nomePai;
	private EstadoCivil estadoCivil;
	private Sexo sexo;
	private String profissao;
	private BigDecimal rendaMensal;
	private Pais pais;
	private Cidade cidade;
	private Estado estado;
	private String regimeCasamento;
	private String totalDependentes;
	private RegistroSistema registroSistema;
	
		
	public PessoaFisica() {
	}

	@CPF
	@NotNull(message="O CPF deve ser informado")
    @NotBlank(message="O CPF deve ser informado")
    @Column(name="pessoa_cpf", length=20, nullable=false)
	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	@DateTimeFormat(iso = ISO.DATE, pattern = "")
	@Temporal(TemporalType.DATE)
	@NotNull(message="A Data de Nascimento deve ser informada")
	@Column(name="pessoa_data_nascimento", nullable=false, columnDefinition="DATE")
	public Date getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	
	@NotBlank(message="O número do RG deve ser informado")
	@Column(name="pessoa_rg", length=100, nullable=false)
	public String getRg() {
		return rg;
	}


	public void setRg(String rg) {
		this.rg = rg;
	}

	
	@NotBlank(message="O Emissor da RG deve ser informado")
	@Column(name="pessoa_emissor_rg", length=100, nullable=false)
	public String getEmissorRg() {
		return emissorRg;
	}


	public void setEmissorRg(String emissorRg) {
		this.emissorRg = emissorRg;
	}


	@DateTimeFormat(iso = ISO.DATE, pattern = "")
	@Temporal(TemporalType.DATE)
	@NotNull(message="A Data de emissão do RG deve ser informada")
	@Column(name="pessoa_data_emissao_rg", nullable=false, columnDefinition="DATE")
	public Date getDataEmissaoRg() {
		return dataEmissaoRg;
	}


	public void setDataEmissaoRg(Date dataEmissaoRg) {
		this.dataEmissaoRg = dataEmissaoRg;
	}

	@Size(min = 2, max = 100, message = "Para o nome da mãe informe de dois a até 100 caracteres ")
	@NotBlank(message="O Nome da mãe deve ser informado")
	@Column(name="pessoa_nome_mae", length=100, nullable=false)
	public String getNomeMae() {
		return nomeMae;
	}


	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	@Size(min = 2, max = 100, message = "Para o nome do Pai informe de dois a até 100 caracteres ")
	@NotBlank(message="O Nome do Pai deve ser informado")
	@Column(name="pessoa_nome_pai", length=100, nullable=false)
	public String getNomePai() {
		return nomePai;
	}


	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

    @NotNull( message="O Estado Cívil deve ser informado ")
    @Enumerated(EnumType.STRING)
    @Column(name="pessoa_estado_civil", length=20, nullable=false)
	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}


	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	@NotNull( message="O Sexo deve ser informado ")
	@Enumerated(EnumType.STRING)
	@Column(name="pessoa_sexo", length=10, nullable=false)
	public Sexo getSexo() {
		return sexo;
	}


	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	@Size(min = 2, max = 100, message = "Para profissão informe de dois a até 100 caracteres ")
	@NotBlank(message="O Nome da profissão deve ser informado")
	@Column(name="pessoa_profissao", length=100, nullable=false)
	public String getProfissao() {
		return profissao;
	}


	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

    @NotNull(message="A Renda Mensal deve ser informada")
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    @DecimalMin("0.0")
    @Column(name="pessoa_renda_mensal", nullable=false)
	public BigDecimal getRendaMensal() {
		return rendaMensal;
	}


	public void setRendaMensal(BigDecimal rendaMensal) {
		this.rendaMensal = rendaMensal;
	}

	@NotNull(message="O nome do país deve ser informado")
	@ManyToOne
	@JoinColumn(name="pais_id", nullable=false,foreignKey=@ForeignKey(name="FK_PESSOA_PAIS"),columnDefinition="BIGINT(20)")
	public Pais getPais() {
		return pais;
	}


	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@NotNull(message="O nome da cidade deve ser informado")
	@ManyToOne
	@JoinColumn(name="cidade_id", foreignKey=@ForeignKey(name="FK_PESSOA_CIDADE"),nullable=false,columnDefinition="BIGINT(20)")
	public Cidade getCidade() {
		return cidade;
	}


	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	@Size(min = 2, max = 50, message = "Para o Regime de casamento informe de dois a até 100 caracteres ")
	@NotBlank(message="O Regime de casamento deve ser informado")
	@Column(name="pessoa_regime_casamento", length=50, nullable=false, columnDefinition="VARCHAR(50)")
	public String getRegimeCasamento() {
		return regimeCasamento;
	}


	public void setRegimeCasamento(String regimeCasamento) {
		this.regimeCasamento = regimeCasamento;
	}


	@NotBlank(message="O total de dependentes deve ser informado")
	@Column(name="pessoa_total_dependentes", length=2, nullable=false)
	public String getTotalDependentes() {
		return totalDependentes;
	}


	public void setTotalDependentes(String totalDependentes) {
		this.totalDependentes = totalDependentes;
	}
		
	@ManyToOne
    @JoinColumn(name="estado_id",foreignKey=@ForeignKey(name="FK_PESSOA_ESTADO"),nullable=false,columnDefinition="BIGINT(20)")
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
		
}
