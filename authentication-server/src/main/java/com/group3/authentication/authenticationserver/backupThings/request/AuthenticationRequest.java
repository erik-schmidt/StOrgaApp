package com.group3.authentication.authenticationserver.backupThings.request;
//https://gitlab.com/ertantoker/tutorials/spring-boot-security-jwt-example/-/tree/master
public class AuthenticationRequest {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
