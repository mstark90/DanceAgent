package com.starkindustriesne.danceagent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http
                .oauth2ResourceServer().jwt();

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/availability/**/book").permitAll()
                .antMatchers("/availability/current").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                // Require authentication for all requests under /api/private
                .anyRequest().authenticated()
                .and().csrf().disable();


    }



}
