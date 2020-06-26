package com.group3.authentication.authenticationserver.service;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.HashMap;
//https://gitlab.com/ertantoker/tutorials/spring-boot-security-jwt-example/-/tree/master
@Component
public class JwtService {

    private String secret;
    private Long expiration;

    public JwtService(){
        this.secret = "mySecret";
        this.expiration = 604800L;
    }

    public String generateJwt(String username){
        final Date createdDate = new Date();
        final Date expirationDate = calculateExpirationDate(createdDate);

        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(username)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 10000);
    }
}
