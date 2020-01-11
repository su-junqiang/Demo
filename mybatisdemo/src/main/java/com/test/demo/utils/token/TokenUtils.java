package com.test.demo.utils.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Component
public class TokenUtils {
    // 签名密钥
    public static final String SECRET = "admin";

    public static String creatJwtToken(String account) {
        String issuer = "sujunqiang";
        String subject = "809962111@qq.com";
        long ttMillis = 15*60000;
        return creatJwtToken(account, issuer, subject, ttMillis);
    }

    private static String creatJwtToken(String account, String issuer, String subject, long ttMillis) {

        //签名算法，将对token签名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //生成签发时间
        long nowMillis = System.currentTimeMillis();
        Date nodw = new Date(nowMillis);
        //通过密钥签名JWT
        byte[] apiKeySercretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySercretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(account)
                .setIssuedAt(nodw).setIssuer(issuer).setSubject(subject)
                .signWith(signatureAlgorithm, signingKey);
        if (ttMillis >= 0) {
            long expMillis = nowMillis + ttMillis;
            Date expDate = new Date(expMillis);
            //过期时间
            builder.setExpiration(expDate);
        }
        return builder.compact();
    }

    public static Claims deToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                .parseClaimsJws(token).getBody();
        return claims;

    }
}
