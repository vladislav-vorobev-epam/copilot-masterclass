package com.epam.masterclass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application class for the Insurance API.
 * This serves as the entry point for the application.
 */
@SpringBootApplication
public class InsuranceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InsuranceApplication.class, args);
        
        System.out.println("\n=============================================================");
        System.out.println("          Insurance Management System Started");
        System.out.println("=============================================================");
        System.out.println("API Documentation is available at: http://localhost:8080/swagger-ui/index.html");
        System.out.println("H2 Database Console: http://localhost:8080/h2-console");
        System.out.println("JDBC URL: jdbc:h2:mem:insurancedb");
        System.out.println("Username: sa");
        System.out.println("Password: password");
        System.out.println("=============================================================\n");
    }
}
