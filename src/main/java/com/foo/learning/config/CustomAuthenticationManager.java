package com.foo.learning.config;

import com.foo.learning.entity.User;
import com.foo.learning.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Primary
public class CustomAuthenticationManager implements AuthenticationManager {
    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        final User user = userRepository.findByName(authentication.getName())
                .orElseThrow(() -> new BadCredentialsException("User Name Not Found Exception"));

        if (!Objects.equals(user.getPassword(), authentication.getCredentials()
                .toString())) {
            throw new BadCredentialsException("Wrong password");
        }
        return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
    }
}
