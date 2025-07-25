package com.assslash.api.enums;

import lombok.Getter;

@Getter
public enum MemberRole {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String role;

    MemberRole(String role) {
        this.role = role;
    }
}