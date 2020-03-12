package com.erp.security.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.erp.auditory.listener.AuditoriaListerner;
import com.erp.auditory.model.Auditoria;
import com.erp.auditory.model.RegistroSistema;
import com.erp.config.util.ErpConfigure;
import com.erp.config.util.anotations.ValidarEmail;
import com.erp.config.util.anotations.ValidarSenha;


@DynamicUpdate(true)
@Entity
@Table(name = "TAB_USUARIO",indexes={@Index(name="USUARIO_ID",columnList="USUARIO_ID"),
        							 @Index(name="USUARIO_EMAIL", columnList="USUARIO_EMAIL")})
@ValidarSenha(senha="password",contraSenha="contraSenha",message="Senhas não conferem...")
@SequenceGenerator(name="usuario_sequence",sequenceName="tab_usuario_sequence", initialValue = 1, allocationSize = 1)
@SQLDelete(sql = "UPDATE TAB_USUARIO SET registro_deletado = true WHERE usuario_id = ?")
@Where(clause=ErpConfigure.NAO_DELETADO)
@EntityListeners(AuditoriaListerner.class)
public class Usuario implements UserDetails, Serializable, Auditoria{

	private Long id;
    private String email;
    private String emailSecundario;
    private String password;
    private String contraSenha;
    private String nome;
    private String username;
    private LocalDate lastLogin;
    private Date dataVencimentoSenha;
    private boolean ativo = false;
    private String foto;
	private String contentType;
    private List<Role> roles;
    private List<UsuarioSenha> usuarioSenhas;
    private RegistroSistema registroSistema;
    
        
    public Usuario() {
	}
    
	public Usuario(Long id, String email, String password, String username, String name, boolean ativo, Set<Role> roles) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.username = username;
		this.nome = name;
		this.ativo = ativo;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="usuario_sequence")
    @Column(name = "usuario_id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ValidarEmail
    @Column(name = "usuario_email",length=100, nullable=false,unique=true)
    @Email(message = "Insira um e-mail válido.")
    @NotEmpty(message = "Insira um e-mail válido.")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@ValidarEmail
    @Column(name = "usuario_email_secundario",length=100, nullable=false,unique=true)
    @Email(message = "Insira um e-mail válido.")
    @NotEmpty(message = "Insira um e-mail válido.")
    public String getEmailSecundario() {
		return emailSecundario;
	}

	public void setEmailSecundario(String emailSecundario) {
		this.emailSecundario = emailSecundario;
	}

	@Column(name = "usuario_password",length=100, nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	   
    @Column(name = "usuario_active", nullable=false)
	public boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	//@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "usuario_last_login",nullable=true)
	public LocalDate getLastLogin() {
		return lastLogin;
	}
	
	public void setLastLogin(LocalDate lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	
	@Column(name = "usuario_nome",length=100, nullable=true)
    @NotNull(message = "Insira um nome válido.")
    @NotEmpty(message = "Insira um nome válido.")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	@Transient
	public String getContraSenha() {
		return contraSenha;
	}

	
	public void setContraSenha(String contraSenha) {
		this.contraSenha = contraSenha;
	}
	
	@PostLoad
	public void postLoad() {
		this.contraSenha = this.password;
	}

	@Where(clause=ErpConfigure.NAO_DELETADO)
	@Size(min = 1, message = "Selecione pelo menos um grupo")
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "TAB_USUARIO_ROLE", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	@Where(clause=ErpConfigure.NAO_DELETADO)
	@OneToMany(mappedBy="usuario")
	public List<UsuarioSenha> getUsuarioSenhas() {
		return usuarioSenhas;
	}

	public void setUsuarioSenhas(List<UsuarioSenha> usuarioSenhas) {
		this.usuarioSenhas = usuarioSenhas;
	}
	
    @Temporal(TemporalType.DATE)
	@Column(name = "usuario_data_vencimento_senha",nullable=true)
	public Date getDataVencimentoSenha() {
		return dataVencimentoSenha;
	}

	public void setDataVencimentoSenha(Date dataVencimentoSenha) {
		this.dataVencimentoSenha = dataVencimentoSenha;
	}
	
	@Column(name="usuario_foto", length=100, nullable=true)
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	@Column(name = "content_type", length=100, nullable=true)
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    	for (Role role : this.getRoles()) {
    		authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getNome().toUpperCase()));
    	}
        return authorities;
	}
	
	@Override
	@Column(name = "usuario_name",length=100, nullable=false, unique=true)
    @NotEmpty(message = "Insira um nome de usuário válido.")
	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	@Override
	@Transient
	public boolean isAccountNonExpired() {
		return ativo;
	}

	@Override
	@Transient
	public boolean isAccountNonLocked() {
		return ativo;
	}

	@Override
	@Transient
	public boolean isCredentialsNonExpired() {
		return ativo;
	}

	@Override
	@Transient
	public boolean isEnabled() {
		return ativo;
	}
	
	
	@Transient
	public String getFotoOuMock() {
		return !StringUtils.isEmpty(foto) ? foto : "users.png";
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
