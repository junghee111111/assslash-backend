package com.assslash.api.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseExpirableTimeEntity extends BaseTimeEntity{
    @Column(name="expired_at", nullable = true)
    private LocalDateTime expiredAt;
}
