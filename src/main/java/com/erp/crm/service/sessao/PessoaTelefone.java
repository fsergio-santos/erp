package com.erp.crm.service.sessao;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.erp.crm.model.Pessoa;
import com.erp.crm.model.Telefone;

@SessionScope
@Component
public class PessoaTelefone {
	
	public Pessoa adicionarAlterarTelefone(Pessoa pessoa, Telefone telefone) {
		Optional<Telefone> telefoneOptional = buscarTelefone(pessoa, telefone);
		Telefone fones = null;
		if (telefoneOptional.isPresent()) {
			fones = telefoneOptional.get();
			int i = pessoa.getTelefones().indexOf(fones);
			fones.setModeloTelefone(telefone.getModeloTelefone());
			fones.setNumeroTelefone(telefone.getNumeroTelefone());
			fones.setTipoTelefone(telefone.getTipoTelefone());
			fones.setPessoa(pessoa);
	        pessoa.getTelefones().add(i,fones);    
		} else {
			pessoa.getTelefones().add(0, telefone);
		}
		return pessoa;
	}
	
	public Pessoa excluirTelefone(Pessoa pessoa, Telefone telefone) {
		int indice = IntStream.range(0, pessoa.getTelefones().size())
				.filter(i -> pessoa.getTelefones().get(i).equals(telefone))
				.findAny().getAsInt();
		pessoa.getTelefones().remove(indice);
		return pessoa;
	}
	
	private Optional<Telefone> buscarTelefone(Pessoa pessoa, Telefone telefone){
		return pessoa.getTelefones().stream()
						.filter(tel -> tel.equals(telefone))
						.findAny();
	}

}
