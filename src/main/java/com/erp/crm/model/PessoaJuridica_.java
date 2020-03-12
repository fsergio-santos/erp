package com.erp.crm.model;

import com.erp.auditory.model.RegistroSistema;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PessoaJuridica.class)
public abstract class PessoaJuridica_ extends com.erp.crm.model.Pessoa_ {

	public static volatile SingularAttribute<PessoaJuridica, String> nomeFantasia;
	public static volatile SingularAttribute<PessoaJuridica, String> naturezaJuridica;
	public static volatile SingularAttribute<PessoaJuridica, String> inscricaoEstadual;
	public static volatile SingularAttribute<PessoaJuridica, String> porteEmpresa;
	public static volatile SingularAttribute<PessoaJuridica, String> inscricaoMunicipal;
	public static volatile SingularAttribute<PessoaJuridica, String> ramoNegocio;
	public static volatile SingularAttribute<PessoaJuridica, RegistroSistema> registroSistema;
	public static volatile SingularAttribute<PessoaJuridica, String> tipoPessoaJuridica;
	public static volatile SingularAttribute<PessoaJuridica, String> cnpj;
	public static volatile SingularAttribute<PessoaJuridica, String> formaPessoaJuridica;
	public static volatile SingularAttribute<PessoaJuridica, Date> dataAbertura;

	public static final String NOME_FANTASIA = "nomeFantasia";
	public static final String NATUREZA_JURIDICA = "naturezaJuridica";
	public static final String INSCRICAO_ESTADUAL = "inscricaoEstadual";
	public static final String PORTE_EMPRESA = "porteEmpresa";
	public static final String INSCRICAO_MUNICIPAL = "inscricaoMunicipal";
	public static final String RAMO_NEGOCIO = "ramoNegocio";
	public static final String REGISTRO_SISTEMA = "registroSistema";
	public static final String TIPO_PESSOA_JURIDICA = "tipoPessoaJuridica";
	public static final String CNPJ = "cnpj";
	public static final String FORMA_PESSOA_JURIDICA = "formaPessoaJuridica";
	public static final String DATA_ABERTURA = "dataAbertura";

}

