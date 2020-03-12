package com.erp.rh.model;

import com.erp.auditory.model.RegistroSistema;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Dependentes.class)
public abstract class Dependentes_ {

	public static volatile SingularAttribute<Dependentes, String> nome_dependente;
	public static volatile SingularAttribute<Dependentes, Date> data_nascimento;
	public static volatile SingularAttribute<Dependentes, String> salario_familia;
	public static volatile SingularAttribute<Dependentes, String> grau_parentesco;
	public static volatile SingularAttribute<Dependentes, RegistroSistema> registroSistema;
	public static volatile SingularAttribute<Dependentes, Long> id;
	public static volatile SingularAttribute<Dependentes, Funcionario> funcionario;
	public static volatile SingularAttribute<Dependentes, String> IRPF;

	public static final String NOME_DEPENDENTE = "nome_dependente";
	public static final String DATA_NASCIMENTO = "data_nascimento";
	public static final String SALARIO_FAMILIA = "salario_familia";
	public static final String GRAU_PARENTESCO = "grau_parentesco";
	public static final String REGISTRO_SISTEMA = "registroSistema";
	public static final String ID = "id";
	public static final String FUNCIONARIO = "funcionario";
	public static final String I_RP_F = "IRPF";

}

