package com.assslash.api.entity;

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
public class Member {
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

    @CreatedDate
    @Column(name="created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Long createdAt;

    @LastModifiedDate
    @Column(name="updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Long updatedAt;

    @Column(name="expired_at", nullable = true)
    private Long expiredAt;
}

