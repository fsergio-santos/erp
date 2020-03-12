package com.erp.config.empresa.model;

import com.erp.auditory.model.RegistroSistema;
import com.erp.crm.model.Cidade;
import com.erp.crm.model.Logradouro;
import com.erp.crm.model.Pais;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Empresa.class)
public abstract class Empresa_ {

	public static volatile SingularAttribute<Empresa, Cidade> cidade;
	public static volatile SingularAttribute<Empresa, MoedaCorrente> moeda;
	public static volatile SingularAttribute<Empresa, Logradouro> endereco;
	public static volatile SingularAttribute<Empresa, String> porteEmpresa;
	public static volatile SingularAttribute<Empresa, String> inscricao_estadual;
	public static volatile SingularAttribute<Empresa, RegistroSistema> registroSistema;
	public static volatile SingularAttribute<Empresa, String> nome;
	public static volatile SingularAttribute<Empresa, Long> id;
	public static volatile SingularAttribute<Empresa, String> cnpj;
	public static volatile SingularAttribute<Empresa, String> nome_fantasia;
	public static volatile SingularAttribute<Empresa, Pais> pais;
	public static volatile SingularAttribute<Empresa, String> inscricao_municipal;

	public static final String CIDADE = "cidade";
	public static final String MOEDA = "moeda";
	public static final String ENDERECO = "endereco";
	public static final String PORTE_EMPRESA = "porteEmpresa";
	public static final String INSCRICAO_ESTADUAL = "inscricao_estadual";
	public static final String REGISTRO_SISTEMA = "registroSistema";
	public static final String NOME = "nome";
	public static final String ID = "id";
	public static final String CNPJ = "cnpj";
	public static final String NOME_FANTASIA = "nome_fantasia";
	public static final String PAIS = "pais";
	public static final String INSCRICAO_MUNICIPAL = "inscricao_municipal";

}

