package com.erp.crm.model;

import com.erp.auditory.model.RegistroSistema;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Estado.class)
public abstract class Estado_ {

	public static volatile SingularAttribute<Estado, String> sigla;
	public static volatile SingularAttribute<Estado, RegistroSistema> registroSistema;
	public static volatile SingularAttribute<Estado, String> nome;
	public static volatile SingularAttribute<Estado, Long> id;
	public static volatile SingularAttribute<Estado, Pais> pais;

	public static final String SIGLA = "sigla";
	public static final String REGISTRO_SISTEMA = "registroSistema";
	public static final String NOME = "nome";
	public static final String ID = "id";
	public static final String PAIS = "pais";

}

