package com.assslash.api.service;

import com.assslash.api.dto.common.RespCode;
import com.assslash.api.dto.common.ResponseDto;
import com.assslash.api.dto.member.RegisterDTO;
import com.assslash.api.entity.Member;
import com.assslash.api.enums.MemberRole;
import com.assslash.api.repository.MemberRepository;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<ResponseDto> registerProcess(RegisterDTO registerDTO) {
        String username = registerDTO.getUsername();
        String password = registerDTO.getPassword();
        String name = registerDTO.getName();

        Boolean isUsernameExists = memberRepository.existsByUsername(username);
        Boolean isNameExists = memberRepository.existsByName(name);

        if (isUsernameExists) {
            return ResponseDto.of(RespCode.REGISTER_USERNAME_EXISTS);
        }

        if (isNameExists) {
            return ResponseDto.of(RespCode.REGISTER_NAME_EXISTS);
        }

        Member newMember = new Member();
        newMember.setUsername(username);
        newMember.setPassword(bCryptPasswordEncoder.encode(password));
        newMember.setName(name);
        newMember.setRole(MemberRole.ROLE_USER);
        memberRepository.save(newMember);

        return ResponseDto.of(RespCode.OK);
    }
}
