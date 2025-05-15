package com.epam.masterclass.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for OpenAPI documentation.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI insuranceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Insurance API")
                        .description("Spring Boot API for Insurance Management")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("EPAM Masterclass")
                                .url("https://www.epam.com")
                                .email("info@epam.com"))
                        .license(new License()
                                .name("Apache License 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("Insurance API Documentation")
                        .url("https://www.epam.com/docs"));
    }
}
