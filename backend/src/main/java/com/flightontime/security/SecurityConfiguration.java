package com.flightontime.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

    @Configuration
    public class SecurityConfiguration {

        /* Se formos usar JWT e ainda é necessário criar a Classe SecurityFilter
        private final SecurityFilter securityFilter;

        public SecurityConfiguration(SecurityFilter securityFilter) {
            this.securityFilter = securityFilter;
        }*/

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            return http
                    .cors(Customizer.withDefaults())
                    .csrf(csrf -> csrf.disable())
                    .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(auth -> auth
                            // Endpoints públicos
                            .requestMatchers("/login").permitAll()
                            .requestMatchers("swagger-ui.html", "swagger-ui/**", "v3/api-docs/**").permitAll()
                            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()   // libera preflight
                            .requestMatchers(HttpMethod.POST, "/predict").permitAll() // libera previsão

                            // O restante exige autenticação
                            .anyRequest().authenticated()
                    )
                    // Usado com JWT
                    //.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();
        }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://127.0.0.1:5500", "http://localhost:5500"));
        config.addAllowedMethod("*");      // inclui OPTIONS, GET, POST, etc.
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);  // útil se futuramente enviar Authorization/cookies

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}