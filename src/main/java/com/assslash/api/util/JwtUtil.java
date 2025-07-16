package com.assslash.api.util;

import com.assslash.api.dto.member.CustomMemberDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {
    private final Key key;
    private final long accessTokenExpTime;

    public JwtUtil(
            @Value("${jwt.secret}") final String secretKey,
            @Value("${jwt.exp_time}") final long accessTokenExpTime
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenExpTime = accessTokenExpTime;
    }

    /**
     *
     * Access Token 생성
     *
     * @param member
     * @return access token string
     */
    public String generateAccessToken(CustomMemberDto member) {
        return createToken(member, accessTokenExpTime);
    }

    /**
     *
     * JWT Builder 로 토큰 생성
     *
     * @param member
     * @param expMs Expiration 시각
     * @return JWT string
     */
    private String createToken(CustomMemberDto member, long expMs) {
        Claims claims = Jwts.claims();

        claims.put("memberId", member.getId());
        claims.put("username", member.getUsername());
        claims.put("name", member.getRole());
        claims.put("role", member.getRole());

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime exp = now.plusSeconds(expMs);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(exp.toInstant()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

    public Long getUserId(String token) {
        return parseClaims(token).get("memberId", Long.class);
    }

    public Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        }catch(ExpiredJwtException e){
            return e.getClaims();
        }
    }

    public boolean isValidToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT",e);
        } catch (ExpiredJwtException e){
            log.info("Expired JWT",e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT",e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty",e);
        }
        return false;
    }
}
