package com.example.rbac.security;

import com.example.rbac.api.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;

import java.util.Optional;

public class UserToken {
    private static final String SECRET = "secret";

    @Getter
    private final String value;

    public UserToken(User user) {
        this.value = generateToken(user);
    }

    public UserToken(String token) {
        this.value = token;
    }

    private String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("password", user.getPassword());
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public Optional<User> extractUser() {
        Optional<User> extractedUser = Optional.empty();
        try {
            Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(value).getBody();
            User user = new User();
            user.setEmail(body.getSubject());
            user.setPassword(body.get("password").toString());
            extractedUser = Optional.of(user);
        } catch (Exception e) {
            // log error ?
        }
        return extractedUser;
    }
}
