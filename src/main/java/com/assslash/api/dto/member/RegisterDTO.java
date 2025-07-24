package com.assslash.api.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDTO {
    private String username;
    private String password;
    private String name;
}
