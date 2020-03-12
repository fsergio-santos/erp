package com.erp.config.empresa.model;

import java.io.Serializable;
import java.util.List;

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
import com.erp.rh.model.DepartamentoFuncionario;

@Entity
@Table(name="TAB_DEPARTAMENTO",indexes={@Index(name="DEPARTAMENTO_ID",columnList="DEPARTAMENTO_ID")},schema="ERP")
@SequenceGenerator(name="departamento_sequence", sequenceName="tab_departamento_sequence", initialValue = 1, allocationSize = 1)
@SQLDelete(sql = "UPDATE TAB_DEPARTAMENTO SET registro_deletado = true WHERE departamento_id = ?")
@Where(clause=ErpConfigure.NAO_DELETADO)
@EntityListeners(AuditoriaListerner.class)
public class Departamento implements Serializable, Auditoria{
	
	private Long id;
	private String nome;
	private Empresa empresa;
	private RegistroSistema registroSistema;
	private List<DepartamentoFuncionario> departamentos;
	
	
	public Departamento() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="departamento_sequence")
	@Column(name="departamento_id",nullable=false)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotBlank(message="O nome do Departamento deve ser informado.")
	@NotNull(message="O nome do departamento deve ser informado.")
	@Column(name="departamento_nome", length=50, nullable=false, columnDefinition="varchar(50)")
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotBlank(message="O nome da empresa deve ser informado.")
	@NotNull(message="O nome da empresa deve ser informado.")
    @ManyToOne(targetEntity=Empresa.class,fetch=FetchType.LAZY)
    @JoinColumn(name="empresa_id",columnDefinition="BIGINT(20)",nullable=false,foreignKey=@ForeignKey(name="FK_DEPARTAMENTO_EMPRESA"))
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Where(clause=ErpConfigure.NAO_DELETADO)
	@OneToMany(mappedBy="departamento")
	public List<DepartamentoFuncionario> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<DepartamentoFuncionario> departamentos) {
		this.departamentos = departamentos;
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
		Departamento other = (Departamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Departamento [id=" + id + ", nome=" + nome + ", empresa=" + empresa + "]";
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
