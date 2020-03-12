package com.erp.auditory.event;

import java.util.Date;

import com.erp.auditory.model.PessoaFisicaUsuario;
import com.erp.auditory.model.PessoaUsuario;
import com.erp.crm.model.Pessoa;
import com.erp.crm.model.PessoaFisica;
import com.erp.security.model.Usuario;

public class PessoaUsuarioEvent {
	
	private PessoaUsuario pessoaUsuario;

	public PessoaUsuarioEvent(Pessoa pessoa, Usuario usuario, String tipoOperacao) {
		pessoaUsuario = new PessoaUsuario(pessoa, usuario, new Date(), tipoOperacao);
	}

	public PessoaUsuario getPessoaUsuario() {
		return pessoaUsuario;
	}
	
	

}
