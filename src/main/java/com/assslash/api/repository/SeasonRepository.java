package com.assslash.api.repository;

import com.assslash.api.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepository extends JpaRepository<Season, Long> {
    Season findFirstByIsCurrent(int isCurrent);
}
