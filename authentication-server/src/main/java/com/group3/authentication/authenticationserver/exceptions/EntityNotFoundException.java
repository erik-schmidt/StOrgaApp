package com.group3.authentication.authenticationserver.exceptions;


public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String msg) {
        super(msg);
    }
}
