package com.SpringAPIGateway.APIConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsFilter() {
        // Create a new CorsConfiguration object
        CorsConfiguration config = new CorsConfiguration();
        
        // Allow requests from http://127.0.0.1:5500
        config.addAllowedOrigin("http://127.0.0.1:5500"); 
        
        // Allow HTTP methods: GET, POST, PUT, DELETE
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        
        // Allow specific headers
        config.addAllowedHeader("Content-Type");
        config.addAllowedHeader("Authorization");
        config.addAllowedHeader("X-Requested-With");
        config.addAllowedHeader("Accept");
        
        // Allow credentials
        config.setAllowCredentials(true);

        // Create a new UrlBasedCorsConfigurationSource and register the CorsConfiguration
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        // Return a new CorsWebFilter with the configured CorsConfigurationSource
        return new CorsWebFilter(source);
    }
}
