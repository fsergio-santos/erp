package com.erp.crm.model;

import com.erp.auditory.model.RegistroSistema;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cidade.class)
public abstract class Cidade_ {

	public static volatile SingularAttribute<Cidade, Estado> estado;
	public static volatile SingularAttribute<Cidade, String> sigla;
	public static volatile SingularAttribute<Cidade, String> ddd;
	public static volatile SingularAttribute<Cidade, Integer> ibge;
	public static volatile ListAttribute<Cidade, Logradouro> logradouros;
	public static volatile SingularAttribute<Cidade, RegistroSistema> registroSistema;
	public static volatile SingularAttribute<Cidade, String> nome;
	public static volatile SingularAttribute<Cidade, Long> id;

	public static final String ESTADO = "estado";
	public static final String SIGLA = "sigla";
	public static final String DDD = "ddd";
	public static final String IBGE = "ibge";
	public static final String LOGRADOUROS = "logradouros";
	public static final String REGISTRO_SISTEMA = "registroSistema";
	public static final String NOME = "nome";
	public static final String ID = "id";

}

