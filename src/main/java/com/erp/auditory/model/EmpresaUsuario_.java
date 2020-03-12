package com.erp.auditory.model;

import com.erp.config.empresa.model.Empresa;
import com.erp.security.model.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EmpresaUsuario.class)
public abstract class EmpresaUsuario_ {

	public static volatile SingularAttribute<EmpresaUsuario, Usuario> usuario;
	public static volatile SingularAttribute<EmpresaUsuario, Long> id;
	public static volatile SingularAttribute<EmpresaUsuario, Empresa> empresa;
	public static volatile SingularAttribute<EmpresaUsuario, String> tipoOperacao;
	public static volatile SingularAttribute<EmpresaUsuario, Date> dataOperacao;

	public static final String USUARIO = "usuario";
	public static final String ID = "id";
	public static final String EMPRESA = "empresa";
	public static final String TIPO_OPERACAO = "tipoOperacao";
	public static final String DATA_OPERACAO = "dataOperacao";

}

