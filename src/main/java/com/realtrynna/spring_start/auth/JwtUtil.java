package com.realtrynna.spring_start.auth;

import com.realtrynna.spring_start.user.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
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
    }

    public String createToken(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String encoded = Base64.getEncoder().encodeToString(secretKey.getBytes());
        byte[] decoded = Base64.getDecoder().decode(encoded);

        Claims claims = Jwts.claims();
        claims.put("email", user.getEmail());

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(expirationTime);

        byte[] temp = new byte[32];
        
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = generator.generateKeyPair().getPublic();
        PrivateKey privateKey = generator.generateKeyPair().getPrivate();

        RSAPrivateKeySpec privateKeySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);
//        RSAPublicKey tempKey = (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
        RSAPrivateKey tempKey = (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date.from(now.toInstant()))
            .setExpiration(Date.from(tokenValidity.toInstant()))
//            .signWith(Keys.hmacShaKeyFor(temp))
            .signWith(tempKey)
            .compact();
    }
}
