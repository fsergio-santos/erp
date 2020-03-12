package com.erp.rh.model;

import com.erp.auditory.model.RegistroSistema;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HorarioTrabalho.class)
public abstract class HorarioTrabalho_ {

	public static volatile SingularAttribute<HorarioTrabalho, Integer> total_horas_mes;
	public static volatile SingularAttribute<HorarioTrabalho, Integer> total_horas_dia;
	public static volatile SingularAttribute<HorarioTrabalho, String> hora_inicio_tarde;
	public static volatile SingularAttribute<HorarioTrabalho, String> hora_fim_tarde;
	public static volatile SingularAttribute<HorarioTrabalho, RegistroSistema> registroSistema;
	public static volatile SingularAttribute<HorarioTrabalho, String> hora_fim_manha;
	public static volatile SingularAttribute<HorarioTrabalho, String> hora_inicio_almoco;
	public static volatile SingularAttribute<HorarioTrabalho, Integer> total_horas_semana;
	public static volatile SingularAttribute<HorarioTrabalho, String> hora_inicio_manha;
	public static volatile SingularAttribute<HorarioTrabalho, String> hora_fim_almoco;
	public static volatile SingularAttribute<HorarioTrabalho, Integer> total_dia_mes;
	public static volatile SingularAttribute<HorarioTrabalho, Long> id;
	public static volatile SingularAttribute<HorarioTrabalho, Funcionario> funcionario;
	public static volatile SingularAttribute<HorarioTrabalho, String> dia_semana;

	public static final String TOTAL_HORAS_MES = "total_horas_mes";
	public static final String TOTAL_HORAS_DIA = "total_horas_dia";
	public static final String HORA_INICIO_TARDE = "hora_inicio_tarde";
	public static final String HORA_FIM_TARDE = "hora_fim_tarde";
	public static final String REGISTRO_SISTEMA = "registroSistema";
	public static final String HORA_FIM_MANHA = "hora_fim_manha";
	public static final String HORA_INICIO_ALMOCO = "hora_inicio_almoco";
	public static final String TOTAL_HORAS_SEMANA = "total_horas_semana";
	public static final String HORA_INICIO_MANHA = "hora_inicio_manha";
	public static final String HORA_FIM_ALMOCO = "hora_fim_almoco";
	public static final String TOTAL_DIA_MES = "total_dia_mes";
	public static final String ID = "id";
	public static final String FUNCIONARIO = "funcionario";
	public static final String DIA_SEMANA = "dia_semana";

}

