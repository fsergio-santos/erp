package com.erp.crm.model;

import com.erp.auditory.model.RegistroSistema;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pessoa.class)
public abstract class Pessoa_ {

	public static volatile SingularAttribute<Pessoa, String> tipodePessoa;
	public static volatile SingularAttribute<Pessoa, String> tipoPessoa;
	public static volatile ListAttribute<Pessoa, Endereco> enderecos;
	public static volatile SingularAttribute<Pessoa, Boolean> telefoneCadastrado;
	public static volatile SingularAttribute<Pessoa, Boolean> enderecoCadastrado;
	public static volatile SingularAttribute<Pessoa, RegistroSistema> registroSistema;
	public static volatile SingularAttribute<Pessoa, String> nome;
	public static volatile SingularAttribute<Pessoa, Long> id;
	public static volatile ListAttribute<Pessoa, Telefone> telefones;
	public static volatile SingularAttribute<Pessoa, String> email;

	public static final String TIPODE_PESSOA = "tipodePessoa";
	public static final String TIPO_PESSOA = "tipoPessoa";
	public static final String ENDERECOS = "enderecos";
	public static final String TELEFONE_CADASTRADO = "telefoneCadastrado";
	public static final String ENDERECO_CADASTRADO = "enderecoCadastrado";
	public static final String REGISTRO_SISTEMA = "registroSistema";
	public static final String NOME = "nome";
	public static final String ID = "id";
	public static final String TELEFONES = "telefones";
	public static final String EMAIL = "email";

}

