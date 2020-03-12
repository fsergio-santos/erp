package com.erp.security.web.registrar;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.erp.security.model.Usuario;
import com.erp.security.service.EmailService;
import com.erp.security.service.RegistrarUsuarioService;

import java.util.UUID;

@Component
public class RegistrarUsuarioListerner implements ApplicationListener<RegistrarUsuario> {
    
	@Autowired
	private RegistrarUsuarioService usuarioService;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	public void onApplicationEvent(RegistrarUsuario registrarUsuario) {
		confirmarRegistroDeUsuario(registrarUsuario);

	}
	
	private void confirmarRegistroDeUsuario(final RegistrarUsuario event) {
        final Usuario usuario = event.getUsuario();
        final String token = UUID.randomUUID().toString();
        usuarioService.criaVerificacaoTokenParaUsuario(usuario, token);
        montarEmailMensagen(event, usuario, token);
    }
	
	private final void montarEmailMensagen(final RegistrarUsuario event, final Usuario usuario, final String token) {
        final String recipientAddress = usuario.getEmail();
        final String subject = "Confirmar Registro";
        final String confirmationUrl = event.getUrl() + "/registrar/confirmar-registro-usuario?token=" + token;
        												 
        final String message = "Confirme o seu registro no sistema "+event.getLocale()+" "+"sua senha de acesso = "+usuario.getPassword();
        final String text = message + " \r\n" + confirmationUrl;
        emailService.sendSimpleMessage(recipientAddress, subject, text, recipientAddress);
    }
	 
}
