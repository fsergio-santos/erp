package com.erp.rh.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat.Style;

import com.erp.auditory.listener.AuditoriaListerner;
import com.erp.auditory.model.Auditoria;
import com.erp.auditory.model.RegistroSistema;
import com.erp.config.util.ErpConfigure;
import com.erp.crm.model.Pessoa;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="TAB_FUNCIONARIO",indexes={@Index(name="FUNCIONARIO_ID",columnList="FUNCIONARIO_ID")})
@PrimaryKeyJoinColumn(name = "funcionario_id")
@SQLDelete(sql = "UPDATE TAB_FUNCIONARIO SET registro_deletado = true WHERE funcionario_id = ?")
@Where(clause=ErpConfigure.NAO_DELETADO)
@EntityListeners(AuditoriaListerner.class)
public class Funcionario extends Pessoa implements Serializable, Auditoria {

	private Integer dependentesImpostoRenda;
	private String  numeroPis;
	private String  numeroCtps;
	private String  tituloEleitor;
	private String  zonaTituloEleitor;
	private String  secaoTituloEleitor;
	private String  ufTituloEleitor;
	private Date    dataContratracao;
	private Date    dataDemissao;
	private Integer tempoServico;
	private Integer jornadaTrabalho;
	private BigDecimal salario;
	private String cbo;
	private String altura;
	private String peso;
	private String cor_cabelos;
	private String cor_olhos;
	private String raca_cor;
	private String deficiente_fisico;
	private String sinais_particulares;
	private String nome_conjuge;
	private List <HorarioTrabalho> horarioTrabalho;
	private String grau_escolaridade;
	private String serie;
	private String curso;
	private List<Dependentes> dependentes;
	private List<DepartamentoFuncionario> departamentos;
	private RegistroSistema registroSistema;
	
		
	@Column(name="funcionario_dependente_irpf",length=2, nullable=false,columnDefinition="VARCHAR(2)")
	public Integer getDependentesImpostoRenda() {
		return dependentesImpostoRenda;
	}
	public void setDependentesImpostoRenda(Integer dependentesImpostoRenda) {
		this.dependentesImpostoRenda = dependentesImpostoRenda;
	}
	
	@NotBlank(message="O número do pis deve ser informado.")
	@NotNull(message="O número do pis deve ser informado.")
	@Column(name="funcionario_numero_pis",length=20, nullable=false,columnDefinition="VARCHAR(20)")
	public String getNumeroPis() {
		return numeroPis;
	}
	public void setNumeroPis(String numeroPis) {
		this.numeroPis = numeroPis;
	}
	
	@NotBlank(message="O número da ctps deve ser informado.")
	@NotNull(message="O número da ctps deve ser informado.")
	@Column(name="funcionario_numero_ctps",length=20, nullable=false,columnDefinition="VARCHAR(20)")
	public String getNumeroCtps() {
		return numeroCtps;
	}
	public void setNumeroCtps(String numeroCtps) {
		this.numeroCtps = numeroCtps;
	}
	
	@NotBlank(message="O número do título eleitor deve ser informado.")
	@NotNull(message="O número do título eleitor deve ser informado.")
	@Column(name="funcionario_titulo_eleitor",length=20, nullable=false,columnDefinition="VARCHAR(20)")
	public String getTituloEleitor() {
		return tituloEleitor;
	}
	public void setTituloEleitor(String tituloEleitor) {
		this.tituloEleitor = tituloEleitor;
	}
	
	@NotBlank(message="O número da zona eleitoral deve ser informado.")
	@NotNull(message="O número da zona eleitoral deve ser informado.")
	@Column(name="funcionario_zona_eleitoral",length=10, nullable=false,columnDefinition="VARCHAR(10)")
	public String getZonaTituloEleitor() {
		return zonaTituloEleitor;
	}
	public void setZonaTituloEleitor(String zonaTituloEleitor) {
		this.zonaTituloEleitor = zonaTituloEleitor;
	}
	
	
	@NotBlank(message="O número da seção eleitoral deve ser informado.")
	@NotNull(message="O número da seção eleitoral deve ser informado.")
	@Column(name="funcionario_secao_eleitoral",length=10, nullable=false,columnDefinition="VARCHAR(10)")
	public String getSecaoTituloEleitor() {
		return secaoTituloEleitor;
	}
	public void setSecaoTituloEleitor(String secaoTituloEleitor) {
		this.secaoTituloEleitor = secaoTituloEleitor;
	}
	
	@NotBlank(message="O estado emissor do pis deve ser informado.")
	@NotNull(message="O estado emissor do pis deve ser informado.")
	@Column(name="funcionario_estado_pis",length=2, nullable=false,columnDefinition="VARCHAR(2)")
	public String getUfTituloEleitor() {
		return ufTituloEleitor;
	}
	public void setUfTituloEleitor(String ufTituloEleitor) {
		this.ufTituloEleitor = ufTituloEleitor;
	}
	
	@NotNull(message="A data deve ser informada.")
	@DateTimeFormat(iso = ISO.DATE, pattern = "")
	@Temporal(TemporalType.DATE)
	@Column(name="funcionario_data_contratacao",nullable=false,columnDefinition="DATE")
	public Date getDataContratracao() {
		return dataContratracao;
	}
	public void setDataContratracao(Date dataContratracao) {
		this.dataContratracao = dataContratracao;
	}
	
	@NotNull(message="A data deve ser informada.")
	@DateTimeFormat(iso = ISO.DATE, pattern = "")
	@Temporal(TemporalType.DATE)
	@Column(name="funcionario_data_demissao",nullable=false,columnDefinition="DATE")
	public Date getDataDemissao() {
		return dataDemissao;
	}
	
	public void setDataDemissao(Date dataDemissao) {
		this.dataDemissao = dataDemissao;
	}
	
	@NotBlank(message="O tempo de serviço deve ser informado.")
	@NotNull(message="O tempo de serviço deve ser informado.")
	@Column(name="funcionario_tempo_servico", nullable=false)
	public Integer getTempoServico() {
		return tempoServico;
	}
	
	public void setTempoServico(Integer tempoServico) {
		this.tempoServico = tempoServico;
	}
	
	@NotBlank(message="A jornada de trabalho deve ser informado.")
	@NotNull(message="A jornada de trabalho deve ser informado.")
	@Column(name="funcionario_jornada_trabalho", nullable=false)
	public Integer getJornadaTrabalho() {
		return jornadaTrabalho;
	}
	
	public void setJornadaTrabalho(Integer jornadaTrabalho) {
		this.jornadaTrabalho = jornadaTrabalho;
	}
	
	
	@NotNull(message="O salário deve ser informado.")
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    @DecimalMin("0.0")
	@Column(name="funcionario_salario", nullable=false)
	public BigDecimal getSalario() {
		return salario;
	}
	
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	
	
	@Where(clause=ErpConfigure.NAO_DELETADO)
	@OneToMany(mappedBy="funcionario")
	public List<DepartamentoFuncionario> getDepartamentos() {
		return departamentos;
	}
	public void setDepartamentos(List<DepartamentoFuncionario> departamentos) {
		this.departamentos = departamentos;
	}
	
	@NotBlank(message="O código CBO deve ser informado.")
	@NotNull(message="O código CBO deve ser informado.")
	@Column(name="funcionario_cbo", nullable=false, length=10, columnDefinition="VARCHAR(10)")
	public String getCbo() {
		return cbo;
	}
	public void setCbo(String cbo) {
		this.cbo = cbo;
	}
	
	@NotBlank(message="A altura deve ser informado.")
	@NotNull(message="A altura deve ser informado.")
	@Column(name="funcionario_altura", nullable=false, length=5, columnDefinition="VARCHAR(5)")
	public String getAltura() {
		return altura;
	}
	public void setAltura(String altura) {
		this.altura = altura;
	}
	
	@NotBlank(message="O peso deve ser informado.")
	@NotNull(message="O peso deve ser informado.")
	@Column(name="funcionario_peso", nullable=false, length=5, columnDefinition="VARCHAR(5)")
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	
	@NotBlank(message="A cor dos cabelos deve ser informado.")
	@NotNull(message="A cor dos cabelos deve ser informado.")
	@Column(name="funcionario_cor_cabelos", nullable=false, length=20, columnDefinition="VARCHAR(20)")
	public String getCor_cabelos() {
		return cor_cabelos;
	}
	public void setCor_cabelos(String cor_cabelos) {
		this.cor_cabelos = cor_cabelos;
	}
	
	@NotBlank(message="A cor dos olhos deve ser informado.")
	@NotNull(message="A cor dos olhos deve ser informado.")
	@Column(name="funcionario_cor_olhos", nullable=false, length=20, columnDefinition="VARCHAR(20)")
	public String getCor_olhos() {
		return cor_olhos;
	}
	public void setCor_olhos(String cor_olhos) {
		this.cor_olhos = cor_olhos;
	}
	
	
	@NotBlank(message="A raça deve ser informada.")
	@NotNull(message="A raça deve ser informado.")
	@Column(name="funcionario_raca", nullable=false, length=20, columnDefinition="VARCHAR(20)")
	public String getRaca_cor() {
		return raca_cor;
	}
	public void setRaca_cor(String raca_cor) {
		this.raca_cor = raca_cor;
	}
	
	@NotBlank(message="Deficiência Física deve ser informada.")
	@NotNull(message="Deficiência Física deve ser informado.")
	@Column(name="funcionario_deficiencia", nullable=false, length=1, columnDefinition="VARCHAR(1)")
	public String getDeficiente_fisico() {
		return deficiente_fisico;
	}
	public void setDeficiente_fisico(String deficiente_fisico) {
		this.deficiente_fisico = deficiente_fisico;
	}
	
	@NotBlank(message="Sinais particulares devem ser informados.")
	@NotNull(message="Sinais particulares devem ser informados.")
	@Column(name="funcionario_sinais", nullable=false, length=255, columnDefinition="VARCHAR(255)")
	public String getSinais_particulares() {
		return sinais_particulares;
	}
	public void setSinais_particulares(String sinais_particulares) {
		this.sinais_particulares = sinais_particulares;
	}
	
	
	@NotBlank(message="O nome do conjuge deve ser informado.")
	@NotNull(message="O nome do conjuge deve ser informado.")
	@Column(name="funcionario_conjuge", nullable=false, length=10, columnDefinition="VARCHAR(10)")
	public String getNome_conjuge() {
		return nome_conjuge;
	}
	public void setNome_conjuge(String nome_conjuge) {
		this.nome_conjuge = nome_conjuge;
	}
	
	
	@OneToMany(mappedBy="funcionario")	
	public List<HorarioTrabalho> getHorarioTrabalho() {
		return horarioTrabalho;
	}

	public void setHorarioTrabalho(List<HorarioTrabalho> horarioTrabalho) {
		this.horarioTrabalho = horarioTrabalho;
	}
	
	
	@NotBlank(message="O grau de escolaridade deve ser informado.")
	@NotNull(message="O grau de escolaridade deve ser informado.")
	@Column(name="funcionario_escolaridade", nullable=false, length=30, columnDefinition="VARCHAR(30)")
	public String getGrau_escolaridade() {
		return grau_escolaridade;
	}
	public void setGrau_escolaridade(String grau_escolaridade) {
		this.grau_escolaridade = grau_escolaridade;
	}
	
	@NotBlank(message="A série cursada deve ser informado.")
	@NotNull(message="O série cursada deve ser informado.")
	@Column(name="funcionario_serie", nullable=false, length=30, columnDefinition="VARCHAR(30)")
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	
	@NotBlank(message="O curso deve ser informado.")
	@NotNull(message="O curso deve ser informado.")
	@Column(name="funcionario_curso", nullable=false, length=30, columnDefinition="VARCHAR(30)")
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	@OneToMany(mappedBy="funcionario")
	public List<Dependentes> getDependentes() {
		return dependentes;
	}
	public void setDependentes(List<Dependentes> dependentes) {
		this.dependentes = dependentes;
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
