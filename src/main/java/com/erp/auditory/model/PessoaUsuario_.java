package com.erp.auditory.model;

import com.erp.crm.model.Pessoa;
import com.erp.security.model.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PessoaUsuario.class)
public abstract class PessoaUsuario_ {

	public static volatile SingularAttribute<PessoaUsuario, Pessoa> pessoa;
	public static volatile SingularAttribute<PessoaUsuario, Usuario> usuario;
	public static volatile SingularAttribute<PessoaUsuario, Long> id;
	public static volatile SingularAttribute<PessoaUsuario, String> tipoOperacao;
	public static volatile SingularAttribute<PessoaUsuario, Date> dataOperacao;

	public static final String PESSOA = "pessoa";
	public static final String USUARIO = "usuario";
	public static final String ID = "id";
	public static final String TIPO_OPERACAO = "tipoOperacao";
	public static final String DATA_OPERACAO = "dataOperacao";

}

