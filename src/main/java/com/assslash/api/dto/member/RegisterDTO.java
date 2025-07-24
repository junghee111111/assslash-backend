package com.assslash.api.dto.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    @NotBlank(message = "사용자 이름은 필수 입력값입니다.")
    @Pattern(
            regexp = "^[a-zA-Z0-9]{8,20}$",
            message = "사용자 이름은 8~20자의 영문자와 숫자만 사용 가능합니다."
    )
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(
            regexp = "^[a-zA-Z0-9]{8,20}$",
            message = "비밀번호는 8~20자의 영문자와 숫자만 사용 가능합니다."
    )
    private String password;

    @NotBlank(message = "이름은 필수 입력값입니다.")
    @Pattern(
            regexp = "^[a-zA-Z0-9가-힣]{1,10}$",
            message = "이름은 최대 10자의 영문자, 숫자, 한글만 사용 가능합니다."
    )
    private String name;
}
