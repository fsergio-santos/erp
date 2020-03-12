package com.erp.security.model;

import com.erp.auditory.model.RegistroSistema;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Escopo.class)
public abstract class Escopo_ {

	public static volatile SingularAttribute<Escopo, RegistroSistema> registroSistema;
	public static volatile SingularAttribute<Escopo, String> nome;
	public static volatile SingularAttribute<Escopo, Long> id;

	public static final String REGISTRO_SISTEMA = "registroSistema";
	public static final String NOME = "nome";
	public static final String ID = "id";

}

