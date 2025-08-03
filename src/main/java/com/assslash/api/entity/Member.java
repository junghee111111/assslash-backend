package com.assslash.api.entity;

import com.assslash.api.entity.common.BaseExpirableTimeEntity;
import com.assslash.api.enums.MemberRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name="member")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseExpirableTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @Column(name = "username", length = 50, updatable = false, unique = true)
    private String username;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="name", nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable = false)
    private MemberRole role;


}

