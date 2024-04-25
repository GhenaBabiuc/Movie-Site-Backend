package com.example.service.user.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Long jwtLifetime;

    @Value("${jwt.refresh-lifetime}")
    private Long refreshLifetime;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> rolesList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("roles", rolesList);

        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + jwtLifetime);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String generateRefreshToken(UserDetails userDetails) {
        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + refreshLifetime);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getUsername(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public List<String> getRoles(String token) {
        return getAllClaimsFromToken(token).get("roles", List.class);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateTokenWithClaims(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String extractUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

}
