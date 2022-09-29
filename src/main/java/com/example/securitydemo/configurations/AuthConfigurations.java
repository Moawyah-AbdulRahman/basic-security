package com.example.securitydemo.configurations;

import com.example.securitydemo.security.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class AuthConfigurations extends WebSecurityConfigurerAdapter {
    private final UserService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                    .antMatchers("/*").hasAnyAuthority("USER")
                    .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
                    .antMatchers("/**").authenticated();
    }
}