package com.erp.rh.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.erp.auditory.listener.AuditoriaListerner;
import com.erp.auditory.model.Auditoria;
import com.erp.auditory.model.RegistroSistema;
import com.erp.config.util.ErpConfigure;
import com.erp.crm.model.Pessoa;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="TAB_DEPENDENTES",indexes={@Index(name="DEPENDENTE_ID",columnList="DEPENDENTE_ID")},schema="ERP")
@SequenceGenerator(name="dependente_sequence", sequenceName="tab_dependente_sequence", initialValue = 1, allocationSize = 1)
@SQLDelete(sql = "UPDATE TAB_DEPENDENTES SET registro_deletado = true WHERE dependente_id = ?")
@Where(clause=ErpConfigure.NAO_DELETADO)
@EntityListeners(AuditoriaListerner.class)
public class Dependentes implements Serializable, Auditoria {
	
	private Long id;
	private String nome_dependente;
	private String grau_parentesco;
	private Date data_nascimento;
	private String IRPF;
	private String salario_familia;
    private Funcionario funcionario;
    private RegistroSistema registroSistema;
    
	
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="dependente_sequence")
	@Column(name="DEPENDENTE_ID")
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotBlank(message="O nome do dependente deve ser informado.")
	@NotNull(message="O nome do dependente deve ser informado.")
	@Column(name="dependente_nome",columnDefinition="VARCHAR(20)",length=20,nullable=false)
    public String getNome_dependente() {
		return nome_dependente;
	}
	public void setNome_dependente(String nome_dependente) {
		this.nome_dependente = nome_dependente;
	}
	
	
	@NotBlank(message="O grau parentesco deve ser informado.")
	@NotNull(message="O grau parentesco deve ser informado.")
	@Column(name="dependente_grau_parentesco",columnDefinition="VARCHAR(20)",length=20,nullable=false)
	public String getGrau_parentesco() {
		return grau_parentesco;
	}
	public void setGrau_parentesco(String grau_parentesco) {
		this.grau_parentesco = grau_parentesco;
	}
	
	
	@NotNull(message="A data deve ser informada.")
	@DateTimeFormat(iso = ISO.DATE, pattern = "")
	@Temporal(TemporalType.DATE)
	@Column(name="dependente_data_nascimento",columnDefinition="DATE",nullable=false)
	public Date getData_nascimento() {
		return data_nascimento;
	}
	
	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	
	@NotBlank(message="Deve informar S ou N  para dependente do imposto de renda.")
	@NotNull(message="Deve informar S ou N  para dependente do imposto de renda.")
	@Column(name="dependente_irpf",columnDefinition="VARCHAR(1)",length=1,nullable=false)
	public String getIRPF() {
		return IRPF;
	}
	
	public void setIRPF(String iRPF) {
		IRPF = iRPF;
	}
	
	@NotBlank(message="Deve informar S ou N  para salário família dependente.")
	@NotNull(message="Deve informar S ou N  para salário família dependente.")
	@Column(name="dependente_salario_familia",columnDefinition="VARCHAR(1)",length=1,nullable=false)
	public String getSalario_familia() {
		return salario_familia;
	}
	public void setSalario_familia(String salario_familia) {
		this.salario_familia = salario_familia;
	}
	
	@WhereJoinTable(clause=ErpConfigure.NAO_DELETADO)
	@Where(clause=ErpConfigure.NAO_DELETADO)
	@JsonIgnoreProperties("dependentes")
	@NotBlank(message="O funcionário deve ser informado.")
	@ManyToOne(targetEntity=Funcionario.class, fetch=FetchType.LAZY)
	@JoinColumn(name="funcionario_id",nullable=false, foreignKey=@ForeignKey(name="FK_DEPENDENTE_FUNCIONARIO"),columnDefinition="BIGINT(20)")
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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
		Dependentes other = (Dependentes) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Dependentes [id=" + id + ", nome_dependente=" + nome_dependente + ", grau_parentesco=" + grau_parentesco
				+ ", data_nascimento=" + data_nascimento + ", IRPF=" + IRPF + ", salario_familia=" + salario_familia
				+ "]";
	}
	
	

}
