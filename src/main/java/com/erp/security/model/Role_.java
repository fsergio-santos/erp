package com.erp.security.model;

import com.erp.auditory.model.RegistroSistema;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Role.class)
public abstract class Role_ {

	public static volatile ListAttribute<Role, RolePermissao> rolePermissao;
	public static volatile SingularAttribute<Role, RegistroSistema> registroSistema;
	public static volatile SingularAttribute<Role, String> nome;
	public static volatile SingularAttribute<Role, Long> id;
	public static volatile ListAttribute<Role, Usuario> usuarios;

	public static final String ROLE_PERMISSAO = "rolePermissao";
	public static final String REGISTRO_SISTEMA = "registroSistema";
	public static final String NOME = "nome";
	public static final String ID = "id";
	public static final String USUARIOS = "usuarios";

}

