package com.assslash.api.web.resolver;

import com.assslash.api.entity.Member;
import com.assslash.api.enums.MemberRole;
import com.assslash.api.web.annotation.BearerToken;
import com.assslash.api.web.jwt.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
public class BearerTokenArgumentResolver implements HandlerMethodArgumentResolver {
    private final JWTUtil jwtUtil;

    public BearerTokenArgumentResolver(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * @param parameter the method parameter to check
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(BearerToken.class)
                && Member.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        BearerToken anno = parameter.getParameterAnnotation(BearerToken.class);
        String header = webRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if (!StringUtils.hasText(header)) {
            if (anno != null && anno.required()) {
                throw new MissingRequestHeaderException(HttpHeaders.AUTHORIZATION, parameter);
            }
            return null;
        }

        String token;
        Member member = new Member();
        // log.info("[BearerTokenArgumentResolver] triggered!");
        if (header.regionMatches(true, 0, "Bearer ", 0, 7)) {
            token = header.substring(7);
            member.setUsername(jwtUtil.getUsername(token));
            member.setRole(MemberRole.valueOf(jwtUtil.getRole(token)));
            member.setPassword("temp");
            // log.info("[BearerTokenArgumentResolver] member: {}", member.getUsername());
        } else {
            throw new IllegalArgumentException("Invalid authorization header");
        }

        if (!StringUtils.hasText(token) && anno != null && anno.required()) {
            throw new MissingRequestHeaderException(HttpHeaders.AUTHORIZATION, parameter);
        }


        return member;
    }
}
