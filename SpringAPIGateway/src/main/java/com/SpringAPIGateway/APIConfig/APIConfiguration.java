package com.SpringAPIGateway.APIConfig;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIConfiguration {
    
    @Bean
    RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            // Route for Grocery Management User service
            .route("GroceryManagementUser", r -> r
                 .path("/user/**")
                 .uri("http://localhost:8035"))
            // Route for Grocery Management Customer service
            .route("GroceryManagementCustomer", r -> r
                .path("/customer/**")
                .uri("http://localhost:8089"))
            // Route for Grocery Management Product service
            .route("GroceryManagementProduct", r -> r
                .path("/product/**")
                .uri("http://localhost:8054"))
            // Route for Grocery Management Sales service
            .route("GroceryManagementSales", r -> r
                    .path("/sales/**")
                    .uri("http://localhost:8051"))
            // Route for Grocery Management Sales Details service
            .route("GroceryManagementSales", r -> r
                    .path("/salesdetails/**")
                    .uri("http://localhost:8051"))
            .build();
    }
}
