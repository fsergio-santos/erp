package com.erp.crm.model;

import com.erp.auditory.model.RegistroSistema;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pais.class)
public abstract class Pais_ {

	public static volatile SingularAttribute<Pais, RegistroSistema> registroSistema;
	public static volatile SingularAttribute<Pais, String> nome;
	public static volatile SingularAttribute<Pais, Long> id;
	public static volatile SingularAttribute<Pais, String> nacionalidade;
	public static volatile ListAttribute<Pais, Estado> estados;

	public static final String REGISTRO_SISTEMA = "registroSistema";
	public static final String NOME = "nome";
	public static final String ID = "id";
	public static final String NACIONALIDADE = "nacionalidade";
	public static final String ESTADOS = "estados";

}

