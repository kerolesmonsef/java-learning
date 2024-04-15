package com.foo.learning.controller;

import com.foo.learning.request.LoginRequest;
import com.foo.learning.response.AuthenticationResponse;
import com.foo.learning.response.BaseResponse;
import com.foo.learning.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService service;

    @PostMapping("/login")
    public BaseResponse<AuthenticationResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        AuthenticationResponse response = service.login(loginRequest);

        return new BaseResponse<>(response);
    }
}
