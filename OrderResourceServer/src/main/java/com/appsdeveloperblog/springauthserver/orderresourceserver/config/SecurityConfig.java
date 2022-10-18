package com.appsdeveloperblog.springauthserver.orderresourceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Make communication STATELESS
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Configure antMatchers if needed
        http
                .authorizeRequests()
                //.antMatchers("/orders/status/check").permitAll() // Public API endpoint
                .anyRequest()
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();


        return http.build();
    }
}
