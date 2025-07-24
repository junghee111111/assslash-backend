package com.assslash.api.config;

import com.assslash.api.jwt.JWTFilter;
import com.assslash.api.jwt.JWTUtil;
import com.assslash.api.jwt.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
    }

    // 암호화 시 사용하려고 bean으로 등록
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // csrf-disable
        http.csrf(AbstractHttpConfigurer::disable);

        // disable form-login
        http.formLogin(AbstractHttpConfigurer::disable);

        // http basic auth disable
        http.httpBasic(AbstractHttpConfigurer::disable);

        // 경로별 인가
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/login", "/auth/register").permitAll()
                .requestMatchers("/swagger/api-docs/**", "/swagger-ui/**").permitAll()
                .requestMatchers("/auth/info").hasAnyRole("ROLE_USER", "ROLE_ADMIN")
                .anyRequest().authenticated()
        );

        // custom filter를 filter chain에 등록
        http.addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);
        http.addFilterAt(
                new LoginFilter(
                        authenticationManager(authenticationConfiguration),
                        jwtUtil
                ), UsernamePasswordAuthenticationFilter.class);

        // session stateless 설정
        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );


        return http.build();
    }
}
