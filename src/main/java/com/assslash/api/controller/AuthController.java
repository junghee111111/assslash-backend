package com.assslash.api.controller;

import com.assslash.api.dto.common.RespCode;
import com.assslash.api.dto.common.ResponseDto;
import com.assslash.api.dto.member.RegisterDTO;
import com.assslash.api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuthController {

    @Autowired
    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<ResponseDto> registerMember(@RequestBody RegisterDTO registerDTO) {
        return authService.registerProcess(registerDTO);
    }

    @GetMapping("info")
    public ResponseEntity<ResponseDto> info() {
        return ResponseDto.of(RespCode.OK);
    }
}
