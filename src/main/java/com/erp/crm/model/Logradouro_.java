package com.erp.crm.model;

import com.erp.auditory.model.RegistroSistema;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Logradouro.class)
public abstract class Logradouro_ {

	public static volatile SingularAttribute<Logradouro, String> tipo;
	public static volatile SingularAttribute<Logradouro, Cidade> cidade;
	public static volatile SingularAttribute<Logradouro, String> sigla;
	public static volatile SingularAttribute<Logradouro, String> complemento;
	public static volatile SingularAttribute<Logradouro, String> descricao_sem_numero;
	public static volatile SingularAttribute<Logradouro, String> descricao_cidade;
	public static volatile SingularAttribute<Logradouro, String> descricao_bairro;
	public static volatile SingularAttribute<Logradouro, Integer> codigo_cidade_ibge;
	public static volatile SingularAttribute<Logradouro, RegistroSistema> registroSistema;
	public static volatile SingularAttribute<Logradouro, Long> id;
	public static volatile SingularAttribute<Logradouro, String> cep;
	public static volatile SingularAttribute<Logradouro, String> descricao;

	public static final String TIPO = "tipo";
	public static final String CIDADE = "cidade";
	public static final String SIGLA = "sigla";
	public static final String COMPLEMENTO = "complemento";
	public static final String DESCRICAO_SEM_NUMERO = "descricao_sem_numero";
	public static final String DESCRICAO_CIDADE = "descricao_cidade";
	public static final String DESCRICAO_BAIRRO = "descricao_bairro";
	public static final String CODIGO_CIDADE_IBGE = "codigo_cidade_ibge";
	public static final String REGISTRO_SISTEMA = "registroSistema";
	public static final String ID = "id";
	public static final String CEP = "cep";
	public static final String DESCRICAO = "descricao";

}

