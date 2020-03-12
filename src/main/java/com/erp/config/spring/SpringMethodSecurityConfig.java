package com.erp.config.spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import com.erp.security.security.CustomPermissionEvaluator;
import com.erp.security.service.UsuarioService;


@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SpringMethodSecurityConfig extends GlobalMethodSecurityConfiguration {
	
	@Lazy
	@Autowired
	private UsuarioService userService;
	
	@Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
		CustomPermissionEvaluator customPermissionEvaluator = new CustomPermissionEvaluator(userService);
 		DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(customPermissionEvaluator);
        return expressionHandler;
    }

	   
 }
