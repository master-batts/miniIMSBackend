package com.testproject.miniIMS.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt.secret}")
    private String JwtSecret;
    @Value("${app.jwt.expiration-ms}")
    private long JwtExpirationDate;

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + JwtExpirationDate);

        String token = Jwts.builder().setSubject(username).setIssuedAt(currentDate).setExpiration(expireDate).signWith(key()).compact();
        return token;
    }

    public Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(JwtSecret)
        );
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();
        return username;
    }
    public boolean validateToken(String token) {
        Jwts.parser().setSigningKey(key()).build().parse(token);
        return true;
    }
}