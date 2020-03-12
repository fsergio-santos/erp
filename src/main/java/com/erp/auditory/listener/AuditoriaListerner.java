package com.erp.auditory.listener;

import java.util.Date;
import java.util.Objects;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import com.erp.auditory.model.Auditoria;
import com.erp.auditory.model.RegistroSistema;

public class AuditoriaListerner {
	
	@PrePersist
	public void onCreate(Auditoria auditoria) {
		RegistroSistema registroSistema = setarRegistroSistema(auditoria);
		registroSistema.setDataInsert(new Date());
		registroSistema.setDataUpdate(new Date());
		registroSistema.setHoraDelete(new Date());
		registroSistema.setHoraUpdate(new Date());
	}
	
	@PreUpdate
	public void onUpdate(Auditoria auditoria) {
		RegistroSistema registroSistema = setarRegistroSistema(auditoria);
		registroSistema.setDataUpdate(new Date());
		registroSistema.setHoraUpdate(new Date());
	}
	
	@PreRemove
	public void onDelete(Auditoria auditoria) {
		RegistroSistema registroSistema = setarRegistroSistema(auditoria);
		registroSistema.setDataDelete(new Date());
		registroSistema.setHoraDelete(new Date());
	}
	
	private RegistroSistema setarRegistroSistema(Auditoria auditoria) {
		RegistroSistema registroSistema = auditoria.getRegistroSistema();
		if (Objects.isNull(registroSistema)) {
            registroSistema = new RegistroSistema();	
            auditoria.setRegistroSistema(registroSistema);
		}
		return registroSistema;
	}

}
