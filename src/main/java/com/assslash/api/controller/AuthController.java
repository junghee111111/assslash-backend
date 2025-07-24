package com.assslash.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("login")
    public ResponseEntity<String> login() {
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @PostMapping("register")
    public ResponseEntity<String> registerMember() {
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }
}
