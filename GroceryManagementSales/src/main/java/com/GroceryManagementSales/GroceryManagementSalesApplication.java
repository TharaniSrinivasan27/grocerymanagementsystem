package com.GroceryManagementSales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
// Specify base packages for entity scanning
@EntityScan(basePackages = {
    "com.GroceryManagementSales.entity",   // Scan for entities in the Sales package
    "com.GroceryManagementProduct.entity", // Scan for entities in the Product package
    "com.GroceryManagementCustomer.Entity" // Scan for entities in the Customer package
})
public class GroceryManagementSalesApplication {

    // Main method to start the Spring Boot application
    public static void main(String[] args) {
        SpringApplication.run(GroceryManagementSalesApplication.class, args);
    }

    // Bean definition for RestTemplate to perform HTTP requests
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
