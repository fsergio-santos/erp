package com.erp.config.empresa.model;

import com.erp.auditory.model.RegistroSistema;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MoedaCorrente.class)
public abstract class MoedaCorrente_ {

	public static volatile SingularAttribute<MoedaCorrente, BigDecimal> paridade_moeda;
	public static volatile SingularAttribute<MoedaCorrente, RegistroSistema> registroSistema;
	public static volatile SingularAttribute<MoedaCorrente, String> nome_moeda;
	public static volatile SingularAttribute<MoedaCorrente, Long> id;
	public static volatile SingularAttribute<MoedaCorrente, String> simbolo_moeda;

	public static final String PARIDADE_MOEDA = "paridade_moeda";
	public static final String REGISTRO_SISTEMA = "registroSistema";
	public static final String NOME_MOEDA = "nome_moeda";
	public static final String ID = "id";
	public static final String SIMBOLO_MOEDA = "simbolo_moeda";

}

