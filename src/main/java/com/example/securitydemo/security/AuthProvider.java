package com.example.securitydemo.security;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthProvider implements AuthenticationProvider {
    private final UserService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication auth) {
        String username = auth.getName();
        String rawPassword = auth.getCredentials().toString();

        var userDetails = userDetailsService.loadUserByUsername(username);

        var encodedPassword = userDetails.getPassword();

        if (userDetails.getUsername().equals(username) && passwordEncoder.matches(rawPassword, encodedPassword) ) {
            return new UsernamePasswordAuthenticationToken(username, rawPassword, userDetails.getAuthorities());
        }

        throw new BadCredentialsException("External system authentication failed");
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}