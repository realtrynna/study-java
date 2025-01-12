package com.realtrynna.spring_start.auth;

import com.realtrynna.spring_start.user.model.User;
import com.realtrynna.spring_start.user.model.request.CreateUserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {
    public String generateAccessToken(final String accessSecret, final Long tokenExpiration, CreateUserDto createUserDto) {
        Long now = System.currentTimeMillis();

        return Jwts.builder()
            .setHeader(createHeader())
            .setClaims(createClaims(createUserDto))
            .setSubject(String.valueOf(createUserDto.getEmail()))
            .setIssuer("realtrynna")
            .signWith(SignatureAlgorithm.RS256, accessSecret)
            .compact();
    }

    /**
     * 헤더
     */
    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();

        header.put("typ", "JWT");
        header.put("alg", "RS256");

        return header;
    }

    /**
     * 페이로드
     */
    private Map<String, Object> createClaims(final CreateUserDto createUserDto) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("email", createUserDto.getEmail());

        return claims;
    }


}
