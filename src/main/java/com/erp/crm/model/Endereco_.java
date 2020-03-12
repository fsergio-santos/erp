package com.erp.crm.model;

import com.erp.auditory.model.RegistroSistema;
import com.erp.crm.model.enumerate.TipoEndereco;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Endereco.class)
public abstract class Endereco_ {

	public static volatile SingularAttribute<Endereco, Cidade> cidade;
	public static volatile SingularAttribute<Endereco, Pessoa> pessoa;
	public static volatile SingularAttribute<Endereco, String> numero;
	public static volatile SingularAttribute<Endereco, Logradouro> logradouro;
	public static volatile SingularAttribute<Endereco, RegistroSistema> registroSistema;
	public static volatile SingularAttribute<Endereco, Long> id;
	public static volatile SingularAttribute<Endereco, TipoEndereco> tipoEndereco;

	public static final String CIDADE = "cidade";
	public static final String PESSOA = "pessoa";
	public static final String NUMERO = "numero";
	public static final String LOGRADOURO = "logradouro";
	public static final String REGISTRO_SISTEMA = "registroSistema";
	public static final String ID = "id";
	public static final String TIPO_ENDERECO = "tipoEndereco";

}

