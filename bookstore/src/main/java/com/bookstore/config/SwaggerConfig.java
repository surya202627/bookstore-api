package com.bookstore.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI bookstoreOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Bookstore API")
                .description("RESTful API for managing books and authors")
                .version("1.0.0")
                .contact(new Contact()
                    .name("Internship Project")
                    .email("intern@bookstore.com"))
                .license(new License()
                    .name("Apache 2.0")
                    .url("http://springdoc.org")));
    }
}