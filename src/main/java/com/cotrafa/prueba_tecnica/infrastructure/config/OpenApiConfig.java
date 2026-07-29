package com.cotrafa.prueba_tecnica.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI prestamosFacilOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Prestamos Fácil API")
                        .description("""
                                API REST para la gestión y evaluación automática
                                de solicitudes de préstamo.
                                """)
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Jhon Quitian")
                                .email("correo@ejemplo.com"))
                        .license(new License()
                                .name("MIT")));
    }
}
