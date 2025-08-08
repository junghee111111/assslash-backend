package com.assslash.api.entity;

import com.assslash.api.entity.common.BaseExpirableTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "season_log")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeasonLog extends BaseExpirableTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "season_log_id")
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "season_id", nullable = false)
    private Long seasonId;

    @Column(name = "win")
    private int win;

    @Column(name = "lose")
    private int lose;

    @Column(name = "draw")
    private int draw;

    @Column(name = "win_rate")
    private double winRate;

    @Column(name = "total_match")
    private int totalMatch;
}
