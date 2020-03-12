package com.erp.auditory.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Embeddable
public class RegistroSistema {
		
	private Date dataInsert;
	private Date horaInsert;
	private Date dataUpdate;
	private Date horaUpdate;
	private Date dataDelete;
	private Date horaDelete;
	private boolean deleted = false;
			
	@DateTimeFormat(iso = ISO.DATE, pattern = "")
	@Temporal(TemporalType.DATE)
	@Column(name="registro_data_insert", nullable=true, columnDefinition="DATE")
	public Date getDataInsert() {
		return dataInsert;
	}
	public void setDataInsert(Date dataInsert) {
		this.dataInsert = dataInsert;
	}
	
	@DateTimeFormat(iso = ISO.DATE, pattern = "")
	@Temporal(TemporalType.DATE)
	@Column(name="registro_data_update", nullable=true, columnDefinition="DATE")
	public Date getDataUpdate() {
		return dataUpdate;
	}
	public void setDataUpdate(Date dataUpdate) {
		this.dataUpdate = dataUpdate;
	}
	
	@DateTimeFormat(iso = ISO.DATE, pattern = "")
	@Temporal(TemporalType.DATE)
	@Column(name="registro_data_delete", nullable=true, columnDefinition="DATE")
	public Date getDataDelete() {
		return dataDelete;
	}
	public void setDataDelete(Date dataDelete) {
		this.dataDelete = dataDelete;
	}
	
	@Column(name="registro_deletado",nullable=false, columnDefinition="boolean default false")	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	@DateTimeFormat(iso = ISO.DATE, pattern = "")
	@Temporal(TemporalType.TIME)
	@Column(name="registro_hora_delete", nullable=true, columnDefinition="DATE")
	public Date getHoraDelete() {
		return horaDelete;
	}
	
	public void setHoraDelete(Date horaDelete) {
		this.horaDelete = horaDelete;
	}

	
	@DateTimeFormat(iso = ISO.DATE, pattern = "")
	@Temporal(TemporalType.TIME)
	@Column(name="registro_hora_insert", nullable=true, columnDefinition="DATE")
	public Date getHoraInsert() {
		return horaInsert;
	}
	
	public void setHoraInsert(Date horaInsert) {
		this.horaInsert = horaInsert;
	}
	
	@DateTimeFormat(iso = ISO.DATE, pattern = "")
	@Temporal(TemporalType.TIME)
	@Column(name="registro_hora_update", nullable=true, columnDefinition="DATE")
	public Date getHoraUpdate() {
		return horaUpdate;
	}
	
	public void setHoraUpdate(Date horaUpdate) {
		this.horaUpdate = horaUpdate;
	}

	
	

}
