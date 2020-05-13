package com.group3.authentication.authenticationserver.backupThings.response;
//https://gitlab.com/ertantoker/tutorials/spring-boot-security-jwt-example/-/tree/master
public class JWTTokenResponse {
    private String token;

    public JWTTokenResponse(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
