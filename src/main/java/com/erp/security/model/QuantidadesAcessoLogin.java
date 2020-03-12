package com.erp.security.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.erp.auditory.listener.AuditoriaListerner;
import com.erp.auditory.model.Auditoria;
import com.erp.auditory.model.RegistroSistema;
import com.erp.config.util.ErpConfigure;

@Entity
@Table(name="TAB_LOGIN_ATTEMPT")
@SequenceGenerator(name="quantidade_sequence",sequenceName="tab_attempt_sequence", initialValue = 1, allocationSize = 1)
@SQLDelete(sql = "UPDATE TAB_LOGIN_ATTEMPT SET registro_deletado = true WHERE login_attempt_id = ?")
@Where(clause=ErpConfigure.NAO_DELETADO)
@EntityListeners(AuditoriaListerner.class)
public class QuantidadesAcessoLogin implements Serializable, Auditoria{
	
	private Long id;
	private String ip;
	private Integer qtdFalhasAcesso;
	private String username;
	private Date horaAcesso;
	private Date dataAcesso;
	private RegistroSistema registroSistema;
	
	public QuantidadesAcessoLogin() {
	}
	
	
	public QuantidadesAcessoLogin(Long id, String ip, Integer qtdFalhasAcesso, Date horaAcesso, Date dataAcesso) {
		this.id = id;
		this.ip = ip;
		this.qtdFalhasAcesso = qtdFalhasAcesso;
		this.horaAcesso = horaAcesso;
		this.dataAcesso = dataAcesso;
	}

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="quantidade_sequence")
    @Column(name="login_attempt_id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="login_attempt_ip",length=130)
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Column(name="login_attempt_qtd_acesso")
	public Integer getQtdFalhasAcesso() {
		return qtdFalhasAcesso;
	}
	public void setQtdFalhasAcesso(Integer qtdFalhasAcesso) {
		this.qtdFalhasAcesso = qtdFalhasAcesso;
	}
	
	@Column(name="login_attempt_username", length=100)	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	@Temporal(TemporalType.TIME)
	@Column(name="login_attempt_hora_acesso")
	public Date getHoraAcesso() {
		return horaAcesso;
	}
	public void setHoraAcesso(Date horaAcesso) {
		this.horaAcesso = horaAcesso;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="login_attempt_data_acesso")
	public Date getDataAcesso() {
		return dataAcesso;
	}
	public void setDataAcesso(Date dataAcesso) {
		this.dataAcesso = dataAcesso;
	}
	
	@Embedded
	@Override
	public RegistroSistema getRegistroSistema() {
		return new RegistroSistema();
	}

	@Override
	public void setRegistroSistema(RegistroSistema registroSistema) {
		this.registroSistema = registroSistema;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuantidadesAcessoLogin other = (QuantidadesAcessoLogin) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "LoginAttempt [id=" + id + ", ip=" + ip + ", qtdFalhasAcesso=" + qtdFalhasAcesso + ", horaAcesso="
				+ horaAcesso + ", dataAcesso=" + dataAcesso + "]";
	}
	
	
	
	
	
	
	

}
