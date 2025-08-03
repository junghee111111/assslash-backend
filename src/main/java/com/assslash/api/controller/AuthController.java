package com.assslash.api.controller;

import com.assslash.api.dto.common.RespCode;
import com.assslash.api.dto.common.ResponseDto;
import com.assslash.api.dto.member.RegisterDTO;
import com.assslash.api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuthController {

    @Autowired
    private final AuthService authService;

    @PostMapping("register")
    public ResponseDto registerMember(RegisterDTO registerDTO) {
        authService.registerProcess(registerDTO);
        return ResponseDto.of(RespCode.OK);
    }

    @GetMapping("info")
    public ResponseDto info() {
        return ResponseDto.of(RespCode.OK);
    }
}
