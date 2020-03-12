package com.erp.config.empresa.model;

import com.erp.auditory.model.RegistroSistema;
import com.erp.rh.model.DepartamentoFuncionario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Departamento.class)
public abstract class Departamento_ {

	public static volatile ListAttribute<Departamento, DepartamentoFuncionario> departamentos;
	public static volatile SingularAttribute<Departamento, RegistroSistema> registroSistema;
	public static volatile SingularAttribute<Departamento, String> nome;
	public static volatile SingularAttribute<Departamento, Long> id;
	public static volatile SingularAttribute<Departamento, Empresa> empresa;

	public static final String DEPARTAMENTOS = "departamentos";
	public static final String REGISTRO_SISTEMA = "registroSistema";
	public static final String NOME = "nome";
	public static final String ID = "id";
	public static final String EMPRESA = "empresa";

}

