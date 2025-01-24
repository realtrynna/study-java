package com.realtrynna.spring_start.auth;

import com.realtrynna.spring_start.user.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SigningKeyResolver;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
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
import java.util.function.Function;
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

    private PrivateKey readPrivateKey() throws Exception {
        String projectDir = System.getProperty("user.dir");
        String privateKeyPath = Paths.get(projectDir, "private.pem").toString();

        byte[] keyBytes = Files.readAllBytes(Paths.get(privateKeyPath));

        String key = new String(keyBytes)
            .replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("-----END PRIVATE KEY-----", "")
            .replaceAll("\n", "")
            .replaceAll("\r", "")
            .replaceAll("\\s", "");

        /**
         * base64로 인코딩된 값을 decoding
         */
        byte[] encoded = Base64.getDecoder().decode(key);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encoded));
    }

    private PublicKey readPublicKey() throws Exception {
        String projectDir = System.getProperty("user.dir");
        String publicKeyPath = Paths.get(projectDir, "public.pem").toString();

        byte[] keyBytes = Files.readAllBytes(Paths.get(publicKeyPath));

        String key = new String(keyBytes)
            .replace("-----BEGIN PUBLIC KEY-----", "")
            .replace("-----END PUBLIC KEY-----", "")
            .replaceAll("\n", "")
            .replaceAll("\r", "")
            .replaceAll("\\s", "");

        byte[] encoded = Base64.getDecoder().decode(key);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(new java.security.spec.X509EncodedKeySpec(encoded));

        return publicKey;
    }

    public String createToken(User user) throws Exception {
        Claims claims = Jwts.claims();
        claims.put("email", user.getEmail());

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(expirationTime);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date.from(now.toInstant()))
            .setExpiration(Date.from(tokenValidity.toInstant()))
            .signWith(SignatureAlgorithm.RS256, (RSAPrivateKey) readPrivateKey())
            .compact();
    }

    public Boolean validateToken(String token) throws Exception {
        try {
            Jwts.parserBuilder().setSigningKey((Key) readPrivateKey()).build().parseClaimsJws(token);

            return true;
        } catch (SecurityException e) {
            System.out.println("토큰 검증 에러 발생" + e.getMessage());
        }

        return false;
    }

    public Claims getBodyFromToken(String token) throws Exception {
        PublicKey publicKey = readPublicKey();

        System.out.println("공개 키" + publicKey);

        return Jwts.parserBuilder()
            .setSigningKey(readPublicKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}
