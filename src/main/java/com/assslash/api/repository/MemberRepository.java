package com.assslash.api.repository;

import com.assslash.api.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Boolean existsByUsername(String username);
    Boolean existsByName(String name);

    Member findByUsername(String username);
}