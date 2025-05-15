package com.epam.masterclass.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Security configuration for the application.
 * Configures security settings for API endpoints.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection for API endpoints
            .authorizeHttpRequests(authorize -> authorize
                // Permit access to H2 console
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                // Permit access to Swagger/OpenAPI documentation
                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
                // Permit access to actuator endpoints
                .requestMatchers(new AntPathRequestMatcher("/actuator/**")).permitAll()
                // Allow read-only operations without authentication
                .requestMatchers(new AntPathRequestMatcher("/api/insurances", "GET")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/insurances/*", "GET")).permitAll()
                // Require authentication for all other operations
                .anyRequest().authenticated()
            )
            .httpBasic(httpBasic -> {}) // Enable HTTP Basic authentication
            // Allow frames for H2 console
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));

        return http.build();
    }
}
