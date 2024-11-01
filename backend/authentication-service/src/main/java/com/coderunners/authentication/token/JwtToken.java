package com.coderunners.authentication.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtToken implements Token {
    private String tokenData;
    private static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public JwtToken(String username) {
        this.tokenData = createToken(username);
    }


    @Override
    public String getTokenData() {
        return tokenData;
    }

    @Override
    public void validate() throws Exception {
        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
        Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(tokenData);
    }

    private String createToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
