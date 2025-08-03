package com.assslash.api.entity.common;

import jakarta.persistence.Column;

public abstract class BaseExpirableTimeEntity extends BaseTimeEntity{
    @Column(name="expired_at", nullable = true)
    private Long expiredAt;
}
