package com.example.securitydemo.security;

import com.example.securitydemo.repositories.SystemUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DbUserDetailsService implements UserDetailsService {

    private final SystemUserRepository systemUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var sysUser = systemUserRepository.findById(username).orElseThrow();

        var userDetails = User
                .withUsername(sysUser.getUsername())
                .password(sysUser.getPassword())
                .authorities(sysUser.getRoles().stream()
                        .map(r->r.getName())
                        .toArray(String[]::new)
                )
                .build();
        return userDetails;
    }
}
