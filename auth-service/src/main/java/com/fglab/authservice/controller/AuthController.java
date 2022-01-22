package com.fglab.authservice.controller;

import com.fglab.authservice.model.TokenRequest;
import com.fglab.authservice.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/token")
    public ResponseEntity getToken(@RequestBody TokenRequest request) {
        return authService.getAccessToken(request).orElseThrow(); // check if present
    }

    @PostMapping("/refresh")
    public ResponseEntity refreshToken(@RequestBody TokenRequest request) {
        return authService.refreshToken(request).orElseThrow();
    }
}
