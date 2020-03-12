package com.erp.auditory.event;

import java.util.Date;

import com.erp.auditory.model.PessoaFisicaUsuario;
import com.erp.auditory.model.PessoaUsuario;
import com.erp.crm.model.Pessoa;
import com.erp.crm.model.PessoaFisica;
import com.erp.security.model.Usuario;

public class PessoaFisicaUsuarioEvent {
	
	private PessoaFisicaUsuario pessoaFisicaUsuario;

	public PessoaFisicaUsuarioEvent(PessoaFisica pessoaFisica, Usuario usuario, String tipoOperacao) {
		pessoaFisicaUsuario = new PessoaFisicaUsuario(pessoaFisica, usuario, new Date(), tipoOperacao);
	}

	public PessoaFisicaUsuario getPessoaFisicaUsuario() {
		return pessoaFisicaUsuario;
	}
	
	

}
