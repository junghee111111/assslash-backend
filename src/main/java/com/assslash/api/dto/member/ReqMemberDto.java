package com.assslash.api.dto.member;

import com.assslash.api.enums.MemberRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqMemberDto {
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$", message = "비밀번호는 8~20자 + 특수문자 1개 이상 조합 해주세요.")
    private String password;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String name;

    private MemberRole role;
}
