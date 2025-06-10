package com.example.test_backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final SecurityConfigImpl securityConfigImpl;
    public SecurityConfig(SecurityConfigImpl securityConfigImpl) {
        this.securityConfigImpl = securityConfigImpl;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return securityConfigImpl.securityFilterChain(http);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        return securityConfigImpl.corsConfigurationSource();
    }
}
