package com.erp.security.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ActiveUserStore {

    public List<String> usuarios_logados;

    public ActiveUserStore() {
    	usuarios_logados = new ArrayList<String>();
    }

    public List<String> getUsers() {
        return usuarios_logados;
    }

    public void setUsers(List<String> usuarios_logados) {
        this.usuarios_logados = usuarios_logados;
    }
}
