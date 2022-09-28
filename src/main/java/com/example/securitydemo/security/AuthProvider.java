package com.example.securitydemo.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;

@Component
public class AuthProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication auth) {
        String username = auth.getName();
        String password = auth.getCredentials().toString();

        if ("user".equals(username) && "pass".equals(password)) {
            var roles = List.of(
                    new SimpleGrantedAuthority("USER")
            );
            return new UsernamePasswordAuthenticationToken(username, password, roles);
        } else if ("admin".equals(username) && "pass".equals(password)){
            var roles = List.of(
                    new SimpleGrantedAuthority("ADMIN"),
                    new SimpleGrantedAuthority("USER")
                    );
            return new UsernamePasswordAuthenticationToken(username, password, roles);
        } else {
            throw new BadCredentialsException("External system authentication failed");
        }
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}