package com.assslash.api.jwt;

import com.assslash.api.dto.member.CustomUserDetails;
import com.assslash.api.entity.Member;
import com.assslash.api.enums.MemberRole;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String rawToken = request.getHeader("Authorization");

        if (rawToken == null || !rawToken.startsWith("Bearer ")) {
            log.error("JWT Token is missing");
            filterChain.doFilter(request, response); // 다음 필터로 필터 체인 넘김
            return; // 메소드 종료
        }

        String token = rawToken.substring(7);
        if (jwtUtil.isExpired(token)) {
            log.error("JWT Token is expired");
            filterChain.doFilter(request, response);
            return;
        }

        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        Member member = new Member();
        member.setUsername(username);
        member.setPassword("temp");
        member.setRole(MemberRole.valueOf(role));

        log.info("username: {}, role: {}", username, role);

        CustomUserDetails customUserDetails = new CustomUserDetails(member);

        // Spring Security 인증 토큰을 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                customUserDetails, null, customUserDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
