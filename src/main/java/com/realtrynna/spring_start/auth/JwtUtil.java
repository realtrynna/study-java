package com.realtrynna.spring_start.auth;

import com.realtrynna.spring_start.user.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private final String secretKey;
    private final Long expirationTime;

    public JwtUtil(
        @Value("${jwt.secretKey}") String secretKey,
        @Value("${jwt.expiration-time}") Long expirationTime
    ) {
        this.secretKey = secretKey;
        this.expirationTime = expirationTime;

        /**
         * base64로 인코딩해보자.
         */
        String encodingSecretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());

        System.out.println("이 값은 일반 ");
    }

    String createToken(User user) {
        return "return";
//        return Jwts.builder()
//            .setClaims(claims)
//            .setIssuedAt(Date.from(now.toInstant()))
//            .setExpiration(Date.from(tokenValidity.toInstant()))
//            .signWith(Keys.hmacShaKeyFor(decodingSecretKey))
//            .compact();
    }
}
