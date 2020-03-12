package com.erp.security.security;

import java.util.List;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.stereotype.Component;

@Component
public class UsuarioLogado implements HttpSessionBindingListener {

    private String username;
    private ActiveUserStore activeUserStore;

    public UsuarioLogado(String username, ActiveUserStore activeUserStore) {
        this.username = username;
        this.activeUserStore = activeUserStore;
    }

    public UsuarioLogado() {
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println(activeUserStore);
        List<String> users = activeUserStore.getUsers();
        UsuarioLogado user = (UsuarioLogado) event.getValue();
        if (!users.contains(user.getUsername())) {
            users.add(user.getUsername());
        }
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        List<String> users = activeUserStore.getUsers();
        UsuarioLogado user = (UsuarioLogado) event.getValue();
        if (users.contains(user.getUsername())) {
            users.remove(user.getUsername());
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
