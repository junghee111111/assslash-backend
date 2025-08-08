package com.assslash.api.controller;

import com.assslash.api.dto.auth.RespMyInfoDTO;
import com.assslash.api.dto.common.ResponseDataDto;
import com.assslash.api.dto.common.ResponseDto;
import com.assslash.api.dto.member.RegisterDTO;
import com.assslash.api.entity.Member;
import com.assslash.api.service.AuthService;
import com.assslash.api.web.annotation.BearerToken;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
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
    public ResponseEntity<ResponseDataDto<RespMyInfoDTO>> info(
            @Parameter(hidden = true)
            @BearerToken Member member
    ) {
        // log.info("[AuthController] member: {}", member.getUsername());
        return authService.getMyInfo(member);
    }
}
