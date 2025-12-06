package com.loginexample.login_example.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI baseOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot Login Example API")
                        .description("JWT-based authentication with roles, email verification and password reset")
                        .version("1.0.0"));
    }
}
