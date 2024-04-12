package org.mmil.Recruitment_MMIL.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.*;

@Component
public class JwtService {

    public String getToken(String userName){
        HashMap<String ,Object> claims = new HashMap<>();
        return createToken(claims , userName);
    }

    public String createToken(HashMap<String,Object> claims , String userName){
        return Jwts.builder()
                .addClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*30))
                .signWith(getKey() , SignatureAlgorithm.HS256).compact();
    }

    public SecretKey getKey(){
        String secret = "9d5d47fb14d372113e5220242aa31c54f671aa62797bd4a655b4b12e1330edb9";
        byte[] key = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(key);
    }

}
