package com.erp.config.empresa.model;

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
	private Date dataUpdate;
	private Date dataDelete;
	private boolean deleted = false;
	
	
	public RegistroSistema() {
	}

	@PrePersist
	public void onCreate() {
		this.setDataInsert(new Date());
		this.setDataUpdate(new Date());
	}
	
	@PreUpdate
	public void onUpdate() {
		this.setDataUpdate(new Date()); 	
	}
	
	@PreRemove
	public void onDelete() {
		this.setDataDelete(new Date());
	}
	
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

}
