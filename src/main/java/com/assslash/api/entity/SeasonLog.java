package com.assslash.api.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "season_log")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeasonLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "season_log_id")
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "season", nullable = false)
    private int season;

    @Column(name = "win")
    private int win;

    @Column(name = "lose")
    private int lose;

    @Column(name = "draw")
    private int draw;

    @Column(name = "win_rate")
    private int winRate;

    @Column(name = "total_match")
    private int totalMatch;

    @CreatedDate
    @Column(name="created_at", nullable = false, updatable = false)
    private Long createdAt;

    @LastModifiedDate
    @Column(name="updated_at", nullable = false)
    private Long updatedAt;
}
