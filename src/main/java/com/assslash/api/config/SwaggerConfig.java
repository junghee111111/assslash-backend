package com.assslash.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Assslash API Documentation")
                .version("v1.0")
                .description("Assslash Backend API docs.");

        return new OpenAPI()
                .info(info);
    }

}
