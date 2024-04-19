package com.foo.learning.service;

import com.foo.learning.entity.User;
import com.foo.learning.repository.UserRepository;
import com.foo.learning.request.LoginRequest;
import com.foo.learning.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    private final JWTService jwtService;
    private final UserRepository userRepository;

    private final AuthenticationManager customAuthenticationManager;

    public AuthenticationResponse login(LoginRequest request) {
        customAuthenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        User user = userRepository.findByName(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setAccessToken(jwtToken);
        authenticationResponse.setRefreshToken(refreshToken);

        return authenticationResponse;
    }
}
