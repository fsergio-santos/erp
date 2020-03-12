package com.erp.rh.model;

import java.io.Serializable;

import javax.persistence.Column;
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
@Table(name="TAB_HORARIO_TRABALHO",indexes={@Index(name="HORARIO_ID",columnList="HORARIO_ID")})
@SequenceGenerator(name="horario_sequence", sequenceName="tab_horario_sequence", initialValue = 1, allocationSize = 1)
@SQLDelete(sql = "UPDATE TAB_HORARIO_TRABALHO SET registro_deletado = true WHERE horario_id = ?")
@Where(clause=ErpConfigure.NAO_DELETADO)
@EntityListeners(AuditoriaListerner.class)
public class HorarioTrabalho implements Serializable, Auditoria {
	
	private Long id;
	private String dia_semana;
	private String hora_inicio_manha;
	private String hora_fim_manha;
	private String hora_inicio_almoco;
	private String hora_fim_almoco;
	private String hora_inicio_tarde;
	private String hora_fim_tarde;
	private int  total_horas_dia;
	private int total_horas_semana;
	private int total_horas_mes;
	private int total_dia_mes;
	private Funcionario funcionario;
	private RegistroSistema registroSistema;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="horario_sequence")
    @Column(name="horario_id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotBlank(message="O dia da semana deve deve ser informado.")
	@NotNull(message="O dia da semana deve deve ser informado.")
	@Column(name="horario_dia", nullable=false, length=10, columnDefinition="VARCHAR(10)")
	public String getDia_semana() {
		return dia_semana;
	}
	public void setDia_semana(String dia_semana) {
		this.dia_semana = dia_semana;
	}
	
	@NotBlank(message="Horário deve deve ser informado.")
	@NotNull(message="Horário deve deve ser informado.")
	@Column(name="horario_inicio_manha", nullable=false, length=5, columnDefinition="VARCHAR(5)")
	public String getHora_inicio_manha() {
		return hora_inicio_manha;
	}
	public void setHora_inicio_manha(String hora_inicio_manha) {
		this.hora_inicio_manha = hora_inicio_manha;
	}
	
	@NotBlank(message="Horário deve deve ser informado.")
	@NotNull(message="Horário deve deve ser informado.")
	@Column(name="horario_fim_manha", nullable=false, length=5, columnDefinition="VARCHAR(5)")
	public String getHora_fim_manha() {
		return hora_fim_manha;
	}
	public void setHora_fim_manha(String hora_fim_manha) {
		this.hora_fim_manha = hora_fim_manha;
	}
	
	@NotBlank(message="Horário deve deve ser informado.")
	@NotNull(message="Horário deve deve ser informado.")
	@Column(name="horario_inicio_almoco", nullable=false, length=5, columnDefinition="VARCHAR(5)")
	public String getHora_inicio_almoco() {
		return hora_inicio_almoco;
	}
	public void setHora_inicio_almoco(String hora_inicio_almoco) {
		this.hora_inicio_almoco = hora_inicio_almoco;
	}
	
	@NotBlank(message="Horário deve deve ser informado.")
	@NotNull(message="Horário deve deve ser informado.")
	@Column(name="horario_fim_almoco", nullable=false, length=5, columnDefinition="VARCHAR(5)")
	public String getHora_fim_almoco() {
		return hora_fim_almoco;
	}
	public void setHora_fim_almoco(String hora_fim_almoco) {
		this.hora_fim_almoco = hora_fim_almoco;
	}
	
	@NotBlank(message="Horário deve deve ser informado.")
	@NotNull(message="Horário deve deve ser informado.")
	@Column(name="horario_inicio_tarde", nullable=false, length=5, columnDefinition="VARCHAR(5)")
	public String getHora_inicio_tarde() {
		return hora_inicio_tarde;
	}
	public void setHora_inicio_tarde(String hora_inicio_tarde) {
		this.hora_inicio_tarde = hora_inicio_tarde;
	}
	
	@NotBlank(message="Horário deve deve ser informado.")
	@NotNull(message="Horário deve deve ser informado.")
	@Column(name="horario_fim_tarde", nullable=false, length=5, columnDefinition="VARCHAR(5)")
	public String getHora_fim_tarde() {
		return hora_fim_tarde;
	}
	public void setHora_fim_tarde(String hora_fim_tarde) {
		this.hora_fim_tarde = hora_fim_tarde;
	}
	
	@NotBlank(message="Total de Horas deve deve ser informado.")
	@NotNull(message="Total de Horas deve deve ser informado.")
	@Column(name="horario_horas_dias", nullable=false, length=5, columnDefinition="VARCHAR(5)")
	public int getTotal_horas_dia() {
		return total_horas_dia;
	}
	public void setTotal_horas_dia(int total_horas_dia) {
		this.total_horas_dia = total_horas_dia;
	}
	
	@NotBlank(message="Total de Horas deve deve ser informado.")
	@NotNull(message="Total de Horas deve deve ser informado.")
	@Column(name="horario_horas_semana", nullable=false, length=5, columnDefinition="VARCHAR(5)")
	public int getTotal_horas_semana() {
		return total_horas_semana;
	}
	public void setTotal_horas_semana(int total_horas_semana) {
		this.total_horas_semana = total_horas_semana;
	}
	
	@NotBlank(message="Total de Horas deve deve ser informado.")
	@NotNull(message="Total de Horas deve deve ser informado.")
	@Column(name="horario_horas_mes", nullable=false, length=5, columnDefinition="VARCHAR(5)")
	public int getTotal_horas_mes() {
		return total_horas_mes;
	}
	public void setTotal_horas_mes(int total_horas_mes) {
		this.total_horas_mes = total_horas_mes;
	}
	
	@NotBlank(message="Total de dias deve deve ser informado.")
	@NotNull(message="Total de dias deve deve ser informado.")
	@Column(name="horario_dias_mes", nullable=false, length=5, columnDefinition="VARCHAR(5)")
	public int getTotal_dia_mes() {
		return total_dia_mes;
	}
	public void setTotal_dia_mes(int total_dia_mes) {
		this.total_dia_mes = total_dia_mes;
	}
	
	@WhereJoinTable(clause=ErpConfigure.NAO_DELETADO)
	@Where(clause=ErpConfigure.NAO_DELETADO)
	@JsonIgnoreProperties("funcionario")
	@ManyToOne(targetEntity=Funcionario.class, fetch=FetchType.LAZY)
	@JoinColumn(name="funcionario_id", nullable=false, foreignKey=@ForeignKey(name="FK_HORARIO_FUNCIONARIO"),columnDefinition="BIGINT(20)")
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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
		HorarioTrabalho other = (HorarioTrabalho) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public RegistroSistema getRegistroSistema() {
		return new RegistroSistema();
	}
	@Override
	public void setRegistroSistema(RegistroSistema registroSistema) {
	    this.registroSistema = registroSistema; 	
	}
	
	

}
