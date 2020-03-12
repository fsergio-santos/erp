package com.erp.rh.model;

import com.erp.auditory.model.RegistroSistema;
import com.erp.config.empresa.model.Departamento;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DepartamentoFuncionario.class)
public abstract class DepartamentoFuncionario_ {

	public static volatile SingularAttribute<DepartamentoFuncionario, Funcao> funcao;
	public static volatile SingularAttribute<DepartamentoFuncionario, Date> dataFim;
	public static volatile SingularAttribute<DepartamentoFuncionario, Departamento> departamento;
	public static volatile SingularAttribute<DepartamentoFuncionario, RegistroSistema> registroSistema;
	public static volatile SingularAttribute<DepartamentoFuncionario, DepartamentoFuncionarioId> id;
	public static volatile SingularAttribute<DepartamentoFuncionario, Funcionario> funcionario;
	public static volatile SingularAttribute<DepartamentoFuncionario, Date> dataInicio;

	public static final String FUNCAO = "funcao";
	public static final String DATA_FIM = "dataFim";
	public static final String DEPARTAMENTO = "departamento";
	public static final String REGISTRO_SISTEMA = "registroSistema";
	public static final String ID = "id";
	public static final String FUNCIONARIO = "funcionario";
	public static final String DATA_INICIO = "dataInicio";

}

