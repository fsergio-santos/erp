package com.erp.rh.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.erp.auditory.listener.AuditoriaListerner;
import com.erp.auditory.model.Auditoria;
import com.erp.auditory.model.RegistroSistema;
import com.erp.config.empresa.model.Departamento;
import com.erp.config.util.ErpConfigure;
import com.erp.rh.model.Funcao;
import com.erp.rh.model.Funcionario;

@Entity
@Table(name="TAB_DEPARTAMENTO_FUNCIONARIO",indexes={@Index(name="DEPARTAMENTO_ID",columnList="DEPARTAMENTO_ID"),
	   	   								            @Index(name="FUNCIONARIO_ID", columnList="FUNCIONARIO_ID")},schema="ERP")
@SQLDelete(sql = "UPDATE TAB_DEPARTAMENTO_FUNCIONARIO SET registro_deletado = true WHERE departamento_id = ?1 and funcioario_id = ?2")
@Where(clause=ErpConfigure.NAO_DELETADO)
@EntityListeners(AuditoriaListerner.class)
public class DepartamentoFuncionario implements Serializable, Auditoria {
	
	private DepartamentoFuncionarioId id;
	private Departamento departamento;
	private Funcionario  funcionario;
	private Date  dataInicio;
	private Date  DataFim;
	private Funcao funcao;
	private RegistroSistema registroSistema;
	
	@EmbeddedId	
	public DepartamentoFuncionarioId getId() {
		return id;
	}
	public void setId(DepartamentoFuncionarioId id) {
		this.id = id;
	}
	
	@WhereJoinTable(clause=ErpConfigure.NAO_DELETADO)
	@Where(clause=ErpConfigure.NAO_DELETADO)
	@ManyToOne
	@JoinColumn(name="departamento_id",nullable=false,insertable=false, updatable=false)
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	@WhereJoinTable(clause=ErpConfigure.NAO_DELETADO)
	@Where(clause=ErpConfigure.NAO_DELETADO)
	@ManyToOne
	@JoinColumn(name="funcionario_id",nullable=false,insertable=false, updatable=false)
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	@NotNull(message="A data deve ser informada.")
	@DateTimeFormat(iso = ISO.DATE, pattern = "")
	@Temporal(TemporalType.DATE)
	@Column(name="departamento_data_inicio",nullable=false,columnDefinition="DATE")
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	@NotNull(message="A data deve ser informada.")
	@DateTimeFormat(iso = ISO.DATE, pattern = "")
	@Temporal(TemporalType.DATE)
	@Column(name="departamento_data_fim",nullable=false,columnDefinition="DATE")
	public Date getDataFim() {
		return DataFim;
	}
	public void setDataFim(Date dataFim) {
		DataFim = dataFim;
	}
	
	@WhereJoinTable(clause=ErpConfigure.NAO_DELETADO)
	@Where(clause=ErpConfigure.NAO_DELETADO)
	@ManyToOne
	@JoinColumn(name="funcao_id",columnDefinition="BIGINT(20)",nullable=false,foreignKey=@ForeignKey(name="FK_DEPARTAMENTO_FUNCAO"))
	public Funcao getFuncao() {
		return funcao;
	}
	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
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
	public String toString() {
		return "DepartamentoFuncionario [id=" + id + ", departamento=" + departamento + ", funcionario=" + funcionario
				+ ", dataInicio=" + dataInicio + ", DataFim=" + DataFim + ", funcao=" + funcao + "]";
	}
	
	

}
