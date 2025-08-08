package com.assslash.api.entity;

import com.assslash.api.entity.common.BaseExpirableTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "battle_log")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BattleLog extends BaseExpirableTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "battle_log_id")
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "opponent_id", nullable = false)
    private Long opponentId;

    @Column(name = "result", nullable = false)
    private boolean result;

    @Column(name = "season", nullable = false)
    private int season;
}
