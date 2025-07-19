package com.assslash.api.dto.member;

import com.assslash.api.enums.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String username;
    private String password;
    private String name;
    private MemberRole role;
}
