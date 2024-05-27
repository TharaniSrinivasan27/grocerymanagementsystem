package com.GroceryManagementCustomer;

//Import the required packages
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient

public class GroceryManagementCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroceryManagementCustomerApplication.class, args);
	}

}
