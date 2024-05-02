package com.foo.learning.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foo.learning.filter.JwtFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain firstFilterChain(HttpSecurity http) throws Exception {
        return
                http
                        .csrf(AbstractHttpConfigurer::disable)
                        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                        .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/auth/login", "/auth/register", "/test/**", "/test","/error","/allow/**")
                                .permitAll()// note that those routes should be existed
                                .anyRequest()
                                .authenticated()
                        )
                        .exceptionHandling(e -> e
                                .authenticationEntryPoint((request, response, authException) -> {
                                    sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage(), request.getRequestURL()
                                            .toString());
                                })
                                .accessDeniedHandler((request, response, accessDeniedException) -> {
                                    sendErrorResponse(response, HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage(), request.getRequestURL()
                                            .toString());
                                })
                        )
                        .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                        .userDetailsService(userDetailsService)
                        .build();
    }

    private void sendErrorResponse(HttpServletResponse response, int status, String message, String path) throws
            IOException {
        response.setContentType("application/json");
        response.setStatus(status);

        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", message);
        errorDetails.put("status", status);
        errorDetails.put("path", path);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(errorDetails);

        response.getWriter()
                .write(json);
    }

}
