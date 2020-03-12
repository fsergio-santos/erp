package com.erp.security.service;

import java.util.List;
import java.util.Optional;

import com.erp.config.util.service.GenericService;
import com.erp.security.model.QuantidadesAcessoLogin;

public interface QuantidadeAcessoLoginService extends GenericService<QuantidadesAcessoLogin, Long>{
	
	Optional<QuantidadesAcessoLogin> updateQtdFalhasAcesso(String username);
	
	void ressetarQtdFalhasAcesso(String username);


}
