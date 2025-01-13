//package com.realtrynna.spring_start.auth;
//
//import com.realtrynna.spring_start.user.model.request.CreateUserDto;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import java.time.ZonedDateTime;
//import java.util.Date;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JwtUtil {
//    private final String secretKey;
//    private final Long expirationTime;
//
//    public JwtUtil(
//        @Value("${jwt.secretKey}") String secretKey,
//        @Value("${jwt.expiration-time}") Long expirationTime
//    ) {
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        this.secretKey = String.valueOf(Keys.hmacShaKeyFor(keyBytes));
//        this.expirationTime = expirationTime;
//    }
//
//    private String createToken(CreateUserDto user) {
//        Claims claims = Jwts.claims();
//        claims.put("email", user.getEmail());
//
//        ZonedDateTime now = ZonedDateTime.now();
//        ZonedDateTime tokenValidity = now.plusSeconds(expirationTime);
//
//        return Jwts.builder()
//            .setClaims(claims)
//            .setIssuedAt(Date.from(now.toInstant()))
//            .setExpiration(Date.from(tokenValidity.toInstant()))
//            .signWith(SignatureAlgorithm.HS256, secretKey)
//            .compact();
//    }
//}
