package com.erp.security.security.custom;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.erp.config.util.data.DataSistema;
import com.erp.security.model.Usuario;
import com.erp.security.security.ActiveUserStore;
import com.erp.security.security.UsuarioLogado;
import com.erp.security.service.UsuarioService;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	private static int TOTALDIAS = 30;
	
	@Autowired
    ActiveUserStore activeUserStore;
	
    @Autowired
    private UsuarioService userService;
    
    @Autowired
    private DataSistema dataSistema;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
  
    	HttpSession session = request.getSession(false);
    	
    	Date dataLogin = new Date();
   
        Usuario usuario = findUsuarioByEmail(auth);
     
        geraCookieUsuario(usuario.getUsername(), response);
     	
        if (Objects.isNull(usuario.getDataVencimentoSenha())) {
        	usuario.setDataVencimentoSenha(new Date());
        }
        
        if (session != null) {
            session.setMaxInactiveInterval(30 * 60);
            UsuarioLogado user = new UsuarioLogado(usuario.getUsername(), activeUserStore);
            session.setAttribute("user", usuario);
        }
        
        updateRegistroUsuario(usuario); 
        
        clearAuthenticationAttributes(request);
        
        handle(request, response, usuario, dataLogin);
        
    }
    
    protected void handle(HttpServletRequest request, 
    		              HttpServletResponse response, 
    		              Usuario usuario, Date dataLogin) throws IOException {
        
    	String targetUrl = determinarUrlSistema(usuario, dataLogin);
        
        if (response.isCommitted()) {
            return;
        }
        
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
    
    private String determinarUrlSistema(Usuario usuario, Date dataLogin) {
    	int totalDias = 0;
    	totalDias = calcularTotalDias(usuario.getDataVencimentoSenha(), dataLogin);
        return ( totalDias >= TOTALDIAS ) ? "/trocar/senha" : "/home";
    }
    
    
    private Usuario findUsuarioByEmail(Authentication auth) {
    	return userService.findUsuarioByEmail(auth.getName()).get();
    }
    
    private void updateRegistroUsuario(Usuario usuario) {
        usuario.setLastLogin(LocalDate.now());
    	userService.updateRegistroUsuario(usuario);
    }
    
    private int calcularTotalDias(Date dataInicial, Date dataFinal) {
    	return dataSistema.totalDiasEntreDatas(dataInicial, dataFinal);
    }
    
    private void geraCookieUsuario(final String user, final HttpServletResponse response) {
        Cookie cookieUsuario = pegarCookieUsuario(user);
        response.addCookie(cookieUsuario);
    }

    private Cookie pegarCookieUsuario(final String user) {
        Cookie cookieUsuario = new Cookie("bemvindo", user);
        cookieUsuario.setMaxAge(60 * 60 * 24 * 30); 
        return cookieUsuario;
    }
    
    protected void clearAuthenticationAttributes(final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

}
