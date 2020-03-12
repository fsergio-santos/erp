package com.erp.auditory.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RegistroSistema.class)
public abstract class RegistroSistema_ {

	public static volatile SingularAttribute<RegistroSistema, Boolean> deleted;
	public static volatile SingularAttribute<RegistroSistema, Date> horaInsert;
	public static volatile SingularAttribute<RegistroSistema, Date> horaUpdate;
	public static volatile SingularAttribute<RegistroSistema, Date> dataInsert;
	public static volatile SingularAttribute<RegistroSistema, Date> dataUpdate;
	public static volatile SingularAttribute<RegistroSistema, Date> dataDelete;
	public static volatile SingularAttribute<RegistroSistema, Date> horaDelete;

	public static final String DELETED = "deleted";
	public static final String HORA_INSERT = "horaInsert";
	public static final String HORA_UPDATE = "horaUpdate";
	public static final String DATA_INSERT = "dataInsert";
	public static final String DATA_UPDATE = "dataUpdate";
	public static final String DATA_DELETE = "dataDelete";
	public static final String HORA_DELETE = "horaDelete";

}

