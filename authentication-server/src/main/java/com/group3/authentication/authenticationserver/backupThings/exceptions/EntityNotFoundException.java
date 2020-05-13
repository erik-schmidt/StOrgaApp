package com.group3.authentication.authenticationserver.backupThings.exceptions;

//https://gitlab.com/ertantoker/tutorials/spring-boot-security-jwt-example/-/tree/master
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String msg) {
        super(msg);
    }
}
