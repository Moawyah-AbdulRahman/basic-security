package com.example.securitydemo.configurations;

import com.example.securitydemo.security.AuthProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class Configurations extends WebSecurityConfigurerAdapter {

    private final AuthProvider authProvider;

    @Override
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/*").hasAnyAuthority("USER")
                .antMatchers("/**").hasAnyAuthority("ADMIN");
    }
}