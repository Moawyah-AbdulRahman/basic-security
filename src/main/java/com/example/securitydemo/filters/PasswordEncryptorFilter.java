package com.example.securitydemo.filters;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@Component
@AllArgsConstructor
public class PasswordEncryptorFilter extends OncePerRequestFilter {

    private final PasswordEncoder passwordEncoder;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        var authHeader = request.getHeader("Authorization");
        byte[] decodedBytes = Base64.getDecoder().decode(authHeader.substring(6));
        String decodedString = new String(decodedBytes);
        var rawPassword = decodedString.split(":")[1];
        var encodedPassword = passwordEncoder.encode(rawPassword);


        filterChain.doFilter(request,response);
    }
}
