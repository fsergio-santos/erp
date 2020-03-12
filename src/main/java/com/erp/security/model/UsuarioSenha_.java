package com.erp.security.model;

import com.erp.auditory.model.RegistroSistema;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UsuarioSenha.class)
public abstract class UsuarioSenha_ {

	public static volatile SingularAttribute<UsuarioSenha, String> senha;
	public static volatile SingularAttribute<UsuarioSenha, RegistroSistema> registroSistema;
	public static volatile SingularAttribute<UsuarioSenha, Usuario> usuario;
	public static volatile SingularAttribute<UsuarioSenha, Long> id;

	public static final String SENHA = "senha";
	public static final String REGISTRO_SISTEMA = "registroSistema";
	public static final String USUARIO = "usuario";
	public static final String ID = "id";

}

