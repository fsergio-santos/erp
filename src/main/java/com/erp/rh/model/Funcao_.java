package com.erp.rh.model;

import com.erp.auditory.model.RegistroSistema;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Funcao.class)
public abstract class Funcao_ {

	public static volatile SingularAttribute<Funcao, RegistroSistema> registroSistema;
	public static volatile SingularAttribute<Funcao, String> nome;
	public static volatile SingularAttribute<Funcao, Long> id;

	public static final String REGISTRO_SISTEMA = "registroSistema";
	public static final String NOME = "nome";
	public static final String ID = "id";

}

