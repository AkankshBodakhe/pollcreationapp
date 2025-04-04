package com.akanksh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

import com.akanksh.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private static final String AUTH_SERVER_URL = "http://localhost:3000/verify-token"; // Node.js server URL

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable()) // Correct Lambda DSL for disabling CSRF
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Correct session policy
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/polls").authenticated()
                .requestMatchers(HttpMethod.PUT, "/polls/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/polls/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/polls/**").authenticated()
                .anyRequest().permitAll()
            )
            .addFilterBefore(jwtAuthenticationFilter(), org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(new RestTemplate(), AUTH_SERVER_URL);
    }
}
