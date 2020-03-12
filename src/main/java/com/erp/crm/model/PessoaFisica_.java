package com.erp.crm.model;

import com.erp.auditory.model.RegistroSistema;
import com.erp.crm.model.enumerate.EstadoCivil;
import com.erp.crm.model.enumerate.Sexo;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PessoaFisica.class)
public abstract class PessoaFisica_ extends com.erp.crm.model.Pessoa_ {

	public static volatile SingularAttribute<PessoaFisica, Cidade> cidade;
	public static volatile SingularAttribute<PessoaFisica, Estado> estado;
	public static volatile SingularAttribute<PessoaFisica, String> profissao;
	public static volatile SingularAttribute<PessoaFisica, Date> dataEmissaoRg;
	public static volatile SingularAttribute<PessoaFisica, RegistroSistema> registroSistema;
	public static volatile SingularAttribute<PessoaFisica, EstadoCivil> estadoCivil;
	public static volatile SingularAttribute<PessoaFisica, String> emissorRg;
	public static volatile SingularAttribute<PessoaFisica, String> totalDependentes;
	public static volatile SingularAttribute<PessoaFisica, Pais> pais;
	public static volatile SingularAttribute<PessoaFisica, BigDecimal> rendaMensal;
	public static volatile SingularAttribute<PessoaFisica, String> rg;
	public static volatile SingularAttribute<PessoaFisica, String> cpf;
	public static volatile SingularAttribute<PessoaFisica, String> nomePai;
	public static volatile SingularAttribute<PessoaFisica, String> regimeCasamento;
	public static volatile SingularAttribute<PessoaFisica, Date> dataNascimento;
	public static volatile SingularAttribute<PessoaFisica, Sexo> sexo;
	public static volatile SingularAttribute<PessoaFisica, String> nomeMae;

	public static final String CIDADE = "cidade";
	public static final String ESTADO = "estado";
	public static final String PROFISSAO = "profissao";
	public static final String DATA_EMISSAO_RG = "dataEmissaoRg";
	public static final String REGISTRO_SISTEMA = "registroSistema";
	public static final String ESTADO_CIVIL = "estadoCivil";
	public static final String EMISSOR_RG = "emissorRg";
	public static final String TOTAL_DEPENDENTES = "totalDependentes";
	public static final String PAIS = "pais";
	public static final String RENDA_MENSAL = "rendaMensal";
	public static final String RG = "rg";
	public static final String CPF = "cpf";
	public static final String NOME_PAI = "nomePai";
	public static final String REGIME_CASAMENTO = "regimeCasamento";
	public static final String DATA_NASCIMENTO = "dataNascimento";
	public static final String SEXO = "sexo";
	public static final String NOME_MAE = "nomeMae";

}

