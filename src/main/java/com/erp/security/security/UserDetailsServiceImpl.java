package com.erp.security.security;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.security.model.Usuario;
import com.erp.security.service.UsuarioService;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private LoginAttemptService loginAttemptService;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
     	final String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }

        Usuario usuario = findUserByUsername(username);

        if ( Objects.isNull(usuario)) {
        	 throw new UsernameNotFoundException("Usuário não encontrado!  "+username);
        }
        
        return new UsuarioSistema(getRolesAndPermissions(usuario));
    }
    
    
    private Usuario getRolesAndPermissions(Usuario usuario) {
 		return usuarioService.findByIdUsuarioRoleAndPermissions(usuario.getId());
	}


	private Usuario findUserByUsername(String username) {
    	return usuarioService.findUsuarioAtivoPeloEmail(username);
    }
	
	
	private final String getClientIP() {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
    
    
}
