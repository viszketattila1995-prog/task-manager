package com.attila.taskmanager.controller;

import com.attila.taskmanager.dto.request.LoginCommand;
import com.attila.taskmanager.dto.response.JwtResponse;
import com.attila.taskmanager.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginWithJwtToken(@Valid @RequestBody LoginCommand command) {
        JwtResponse response = authService.loginWithJwtToken(command);
        return ResponseEntity.ok(response);
    }
}
