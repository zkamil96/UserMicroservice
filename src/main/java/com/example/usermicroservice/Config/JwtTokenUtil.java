package com.example.usermicroservice.Config;

import com.example.usermicroservice.Model.Users;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenUtil {
    @Value("${jwt.duration}")
    private long EXPIRE_DURATION;
    @Value("${jwt.secret}")
    private String secretKey;

    public String generateAccessToken(Users user){
        return Jwts.builder()
                .setSubject(user.getId() + "," + user.getUsername())
                .setIssuer("Users Microservice")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public boolean validateAccessToken(String token){
        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            return true;

        } catch (ExpiredJwtException e) {
            log.error("JWT token expired");
        } catch (UnsupportedJwtException e) {
            log.error("JWT is not supported");
        } catch (MalformedJwtException e) {
            log.error("JWT is invalid");
        } catch (SignatureException e) {
            log.error("Signature validation failed");
        } catch (IllegalArgumentException e) {
            log.error("Token is null");
        }
        return false;
    }

    public String getSubject(String token){
        return parseClaims(token).getSubject();
    }

    private Claims parseClaims(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
