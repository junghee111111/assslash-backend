package com.assslash.api.dto.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.function.Predicate;

@Getter
@RequiredArgsConstructor
public enum RespCode {
    OK(200, HttpStatus.OK, "OK"),
    INTERNAL_ERROR(500, HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),
    METHOD_NOT_ALLOWED(403, HttpStatus.METHOD_NOT_ALLOWED, "Method not allowed!"),
    REGISTER_USERNAME_EXISTS(403, HttpStatus.METHOD_NOT_ALLOWED, "USERNAME_EXISTS"),
    REGISTER_NAME_EXISTS(403, HttpStatus.METHOD_NOT_ALLOWED, "NAME_EXISTS"),
    FORBIDDEN(401, HttpStatus.FORBIDDEN, "Forbidden"),
    MEMBER_NOT_FOUND(404, HttpStatus.NOT_FOUND, "MEMBER_NOT_FOUND");

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;

    public String getMessage(Throwable t) {
        return this.getMessage(this.getMessage(this.getMessage() + " - " + t.getMessage()));
    }

    public String getMessage(String message) {
        return Optional.ofNullable(message)
                .filter(Predicate.not(String::isBlank))
                .orElse(this.getMessage());
    }
}
