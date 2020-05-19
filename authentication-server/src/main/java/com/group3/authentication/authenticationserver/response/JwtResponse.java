package com.group3.authentication.authenticationserver.response;

public class JwtResponse {

    private String token;
    private String matrNr;

    public JwtResponse(String token, String matrNr) {
        this.token = token;
        this.matrNr = matrNr;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMatrNr() {
        return matrNr;
    }

    public void setMatrNr(String matrNr) {
        this.matrNr = matrNr;
    }
}
