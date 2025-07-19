package com.assslash.api.dto.member;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReqLoginDto {

    @NotNull(message = "아이디 입력은 필수입니다.")
    private String username;

    @NotNull(message = "패스워드 입력은 필수입니다.")
    private String password;
}