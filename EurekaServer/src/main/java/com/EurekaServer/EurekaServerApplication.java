package com.EurekaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEurekaServer // Enables this application as a Eureka server
public class EurekaServerApplication {

    public static void main(String[] args) {
        // Run the Eureka server application
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
