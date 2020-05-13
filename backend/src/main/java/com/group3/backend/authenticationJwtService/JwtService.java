package com.group3.backend.authenticationJwtService;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.HashMap;

@Component
public class JwtService {

    private final String SECRET = "lkaasdfIHATESTUDYkasfdhaskhfdhasdfasdf534sdf465a4dfASDFASFDA";
    private final Long EXPIRATION = 3000000L;

    public String generateJwt(String username){
        final Date createdDate = new Date();
        final Date expirationDate = calculateExpirationDate(createdDate);

        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(username)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + EXPIRATION * 10000);
    }
}
