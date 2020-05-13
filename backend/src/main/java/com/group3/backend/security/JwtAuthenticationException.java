package com.group3.backend.security;

import org.springframework.security.core.AuthenticationException;
//https://gitlab.com/ertantoker/tutorials/spring-boot-security-jwt-example/-/tree/master
public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}

