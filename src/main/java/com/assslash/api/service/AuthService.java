package com.assslash.api.service;

import com.assslash.api.dto.member.RegisterDTO;
import com.assslash.api.entity.Member;
import com.assslash.api.enums.MemberRole;
import com.assslash.api.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(
            MemberRepository memberRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Boolean registerProcess(RegisterDTO registerDTO) {
        String username = registerDTO.getUsername();
        String password = registerDTO.getPassword();
        String name = registerDTO.getName();

        Boolean isExiststUsername = memberRepository.existsByUsername(username);
        Boolean isExiststName = memberRepository.existsByName(name);
        if (isExiststUsername || isExiststName) {
            return false;
        }

        Member newMember = new Member();
        newMember.setUsername(username);
        newMember.setPassword(bCryptPasswordEncoder.encode(password));
        newMember.setName(name);
        newMember.setRole(MemberRole.USER);
        memberRepository.save(newMember);

        return true;
    }
}
