package com.assslash.api.service;

import com.assslash.api.dto.member.CustomUserDetails;
import com.assslash.api.entity.Member;
import com.assslash.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("CustomUserDetailsService.loadUserByUsername() " + username);
        Member member = memberRepository.findByUsername(username);
        if (member != null) {
            return new CustomUserDetails(member);
        }
        return null;
    }
}
