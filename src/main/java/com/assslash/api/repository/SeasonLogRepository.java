package com.assslash.api.repository;

import com.assslash.api.entity.SeasonLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeasonLogRepository extends JpaRepository<SeasonLog, Long> {
    List<SeasonLog> findByMemberId(Long memberId);

    SeasonLog findFirstByMemberIdAndSeasonId(Long memberId, Long seasonId);


}
