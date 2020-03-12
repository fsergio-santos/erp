package com.erp.security.security;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.erp.config.util.data.DataSistema;
import com.erp.security.model.Usuario;
import com.erp.security.service.UsuarioService;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	private static int TOTALDIAS = 30;
	
    @Autowired
    private UsuarioService userService;
    
    @Autowired
    private DataSistema dataSistema;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
        int totalDias = 0;
    	Date dataLogin = new Date();
        Usuario usuario = findUsuarioByEmail(auth);
        if (Objects.isNull(usuario.getDataVencimentoSenha())) {
        	usuario.setDataVencimentoSenha(new Date());
        }
        updateRegistroUsuario(usuario); 
        totalDias = calcularTotalDias(usuario.getDataVencimentoSenha(), dataLogin);
        if ( totalDias >= TOTALDIAS ) {
        	redirectStrategy.sendRedirect(request, response, "/trocar/senha");
        } else {
        	redirectStrategy.sendRedirect(request, response, "/home");
        }
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

}
