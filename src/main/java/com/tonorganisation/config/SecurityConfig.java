package com.tonorganisation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Désactive CSRF si non nécessaire
            .cors(cors -> {}) // Active les règles CORS définies ailleurs
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/api/**").permitAll() // Autorise toutes les requêtes sous /api/users/
                .anyRequest().authenticated() // Toutes les autres routes nécessitent une authentification
            );

        return http.build();
    }
}

