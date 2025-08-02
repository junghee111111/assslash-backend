package com.assslash.api.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "battle_log")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BattleLog {
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

    @CreatedDate
    @Column(name="created_at", nullable = false, updatable = false)
    private Long createdAt;
}
