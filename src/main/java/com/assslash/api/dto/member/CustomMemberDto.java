package com.assslash.api.dto.member;

import com.assslash.api.enums.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomMemberDto extends MemberDto {
    private Long memberId;
    private MemberRole role;
}
