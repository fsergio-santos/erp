package com.erp.rh.model;

import com.erp.auditory.model.RegistroSistema;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Funcionario.class)
public abstract class Funcionario_ extends com.erp.crm.model.Pessoa_ {

	public static volatile SingularAttribute<Funcionario, String> nome_conjuge;
	public static volatile ListAttribute<Funcionario, Dependentes> dependentes;
	public static volatile SingularAttribute<Funcionario, String> zonaTituloEleitor;
	public static volatile ListAttribute<Funcionario, DepartamentoFuncionario> departamentos;
	public static volatile ListAttribute<Funcionario, HorarioTrabalho> horarioTrabalho;
	public static volatile SingularAttribute<Funcionario, Date> dataDemissao;
	public static volatile SingularAttribute<Funcionario, String> deficiente_fisico;
	public static volatile SingularAttribute<Funcionario, Integer> jornadaTrabalho;
	public static volatile SingularAttribute<Funcionario, Date> dataContratracao;
	public static volatile SingularAttribute<Funcionario, String> cor_olhos;
	public static volatile SingularAttribute<Funcionario, String> secaoTituloEleitor;
	public static volatile SingularAttribute<Funcionario, String> raca_cor;
	public static volatile SingularAttribute<Funcionario, String> cor_cabelos;
	public static volatile SingularAttribute<Funcionario, String> peso;
	public static volatile SingularAttribute<Funcionario, BigDecimal> salario;
	public static volatile SingularAttribute<Funcionario, String> grau_escolaridade;
	public static volatile SingularAttribute<Funcionario, RegistroSistema> registroSistema;
	public static volatile SingularAttribute<Funcionario, Integer> tempoServico;
	public static volatile SingularAttribute<Funcionario, String> sinais_particulares;
	public static volatile SingularAttribute<Funcionario, String> ufTituloEleitor;
	public static volatile SingularAttribute<Funcionario, String> cbo;
	public static volatile SingularAttribute<Funcionario, String> numeroPis;
	public static volatile SingularAttribute<Funcionario, String> altura;
	public static volatile SingularAttribute<Funcionario, String> curso;
	public static volatile SingularAttribute<Funcionario, String> serie;
	public static volatile SingularAttribute<Funcionario, Integer> dependentesImpostoRenda;
	public static volatile SingularAttribute<Funcionario, String> tituloEleitor;
	public static volatile SingularAttribute<Funcionario, String> numeroCtps;

	public static final String NOME_CONJUGE = "nome_conjuge";
	public static final String DEPENDENTES = "dependentes";
	public static final String ZONA_TITULO_ELEITOR = "zonaTituloEleitor";
	public static final String DEPARTAMENTOS = "departamentos";
	public static final String HORARIO_TRABALHO = "horarioTrabalho";
	public static final String DATA_DEMISSAO = "dataDemissao";
	public static final String DEFICIENTE_FISICO = "deficiente_fisico";
	public static final String JORNADA_TRABALHO = "jornadaTrabalho";
	public static final String DATA_CONTRATRACAO = "dataContratracao";
	public static final String COR_OLHOS = "cor_olhos";
	public static final String SECAO_TITULO_ELEITOR = "secaoTituloEleitor";
	public static final String RACA_COR = "raca_cor";
	public static final String COR_CABELOS = "cor_cabelos";
	public static final String PESO = "peso";
	public static final String SALARIO = "salario";
	public static final String GRAU_ESCOLARIDADE = "grau_escolaridade";
	public static final String REGISTRO_SISTEMA = "registroSistema";
	public static final String TEMPO_SERVICO = "tempoServico";
	public static final String SINAIS_PARTICULARES = "sinais_particulares";
	public static final String UF_TITULO_ELEITOR = "ufTituloEleitor";
	public static final String CBO = "cbo";
	public static final String NUMERO_PIS = "numeroPis";
	public static final String ALTURA = "altura";
	public static final String CURSO = "curso";
	public static final String SERIE = "serie";
	public static final String DEPENDENTES_IMPOSTO_RENDA = "dependentesImpostoRenda";
	public static final String TITULO_ELEITOR = "tituloEleitor";
	public static final String NUMERO_CTPS = "numeroCtps";

}

