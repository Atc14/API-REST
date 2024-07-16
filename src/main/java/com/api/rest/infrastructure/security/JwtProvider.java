package com.api.rest.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    private final static SecretKey TOKEN_SECRET_KEY = Jwts.SIG.HS512.key()
                                                                    .build();
    private final static Long EXPIRATION_SECONDS_TOKEN = 3600000L;


    public String buildToken(UserDetails userDetails) {
        return Jwts.builder()
                   .subject(userDetails.getUsername())
                   .issuedAt(new Date(System.currentTimeMillis()))
                   .expiration(new Date(System.currentTimeMillis() + EXPIRATION_SECONDS_TOKEN))
                   .signWith(getSignInKey())
                   .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                   .verifyWith(getSignInKey())
                   .build()
                   .parseSignedClaims(token)
                   .getPayload();
    }

    public String getSubject(String token) {
        return Jwts.parser()
                   .verifyWith(getSignInKey())
                   .build()
                   .parseSignedClaims(token)
                   .getPayload()
                   .getSubject();
    }

    public Boolean validate(String token) {
        try {
            Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
            return true;
        } catch (ExpiredJwtException | IllegalArgumentException | UnsupportedJwtException | MalformedJwtException e) {
            return false;
        }
    }

    private SecretKey getSignInKey() {
        return TOKEN_SECRET_KEY;
    }
}