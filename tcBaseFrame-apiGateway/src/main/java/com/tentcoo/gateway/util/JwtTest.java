package com.tentcoo.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rover on 2018/2/28.
 */
public class JwtTest {

    private static String apiKey = "w-oasis123456";

    private static final String  SKEY    = "abcdefgh";
    private static final Charset CHARSET = Charset.forName("gb2312");

    public static void main(String[] args){
        testA();
        String token = createJWT("uid1231323asc", "issuerX", "subjectX", 7200);
        System.out.println("token="+token+",len="+token.length());
        parseJWT(token);

        /*String encryptResult = DesUtil.encrypt(token, CHARSET, SKEY);
        System.out.println(encryptResult);
        // 直接将如上内容解密
        String decryResult = "";
        try {
            decryResult = DesUtil.decrypt(encryptResult, CHARSET, SKEY);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println(decryResult);*/

        /*String s = AesUtil.encrypt(token, SKEY);
        System.out.println("加密后："+s);
        String s1 = AesUtil.decrypt(s, SKEY);
        System.out.println("解密后：" +s1);*/
    }

    public static void testA(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", "uid1231323asd");
        claims.put("created", new Date());
        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS256, apiKey)
                .compact();
        System.out.println("token="+token);
    }

    private static Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + 7200 * 1000);
    }

    private static String createJWT(String id, String issuer, String subject, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis*1000;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    private static void parseJWT(String jwt) {
        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(apiKey))
                .parseClaimsJws(jwt).getBody();
        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());
    }

}
