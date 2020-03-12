package com.erp.auditory.model;

import com.erp.crm.model.PessoaFisica;
import com.erp.security.model.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PessoaFisicaUsuario.class)
public abstract class PessoaFisicaUsuario_ {

	public static volatile SingularAttribute<PessoaFisicaUsuario, PessoaFisica> pessoaFisica;
	public static volatile SingularAttribute<PessoaFisicaUsuario, Usuario> usuario;
	public static volatile SingularAttribute<PessoaFisicaUsuario, Long> id;
	public static volatile SingularAttribute<PessoaFisicaUsuario, String> tipoOperacao;
	public static volatile SingularAttribute<PessoaFisicaUsuario, Date> dataOperacao;

	public static final String PESSOA_FISICA = "pessoaFisica";
	public static final String USUARIO = "usuario";
	public static final String ID = "id";
	public static final String TIPO_OPERACAO = "tipoOperacao";
	public static final String DATA_OPERACAO = "dataOperacao";

}

