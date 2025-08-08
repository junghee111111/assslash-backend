package com.assslash.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "season")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "season_id")
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "is_current", columnDefinition = "TINYINT(1) DEFAULT 0")
    private int isCurrent;
}
