package com.assslash.api.service;

import com.assslash.api.dto.member.CustomMemberDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;

@Service
public class JwtService {
    private final String secretKey = "mySecretKey";
    private final long expMs = 86400000;

    public String generateToken(String username) {
        //return Jwts.builder();
        return "";
    }
}
