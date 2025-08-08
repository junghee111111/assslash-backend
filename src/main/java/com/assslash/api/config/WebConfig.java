package com.assslash.api.config;

import com.assslash.api.web.jwt.JWTUtil;
import com.assslash.api.web.resolver.BearerTokenArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final JWTUtil jwtUtil;

    public WebConfig(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new BearerTokenArgumentResolver(jwtUtil));
    }
}
