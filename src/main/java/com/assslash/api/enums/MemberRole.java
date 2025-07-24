package com.assslash.api.enums;

import lombok.Getter;

@Getter
public enum MemberRole {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String role;

    MemberRole(String role) {
        this.role = role;
    }
}