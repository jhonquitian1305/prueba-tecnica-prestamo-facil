package com.cotrafa.prueba_tecnica.infrastructure.web.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Información necesaria para registrar un usuario")
public record UserDTO(

        @Schema(description = "Nombres del usuario", example = "John")
        @NotEmpty(message = "El nombre es obligatorio")
        String name,

        @Schema(description = "Apellidos del usuario", example = "Doe")
        @NotEmpty(message = "Los apellidos son obligatorios")
        String lastname,

        @Schema(description = "Correo electrónico", example = "john.doe@mail.com")
        @NotEmpty(message = "El email es obligatorio")
        @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$", message = "Debe ser un email válido")
        String email,

        @Schema(description = "Tipo de documento", example = "CC")
        @NotEmpty(message = "El tipo de identificacion es obligatorio")
        String typeIdentification,

        @Schema(description = "Número de documento", example = "123456789")
        @NotEmpty(message = "El número de identificación es obligatorio")
        String identification,

        @Schema(description = "Salario base", example = "3500000")
        @NotNull(message = "El salario base es obligatorio")
        @Min(value = 0, message = "El salario base debe ser mayor o igual a cero")
        @Max(value = 15_000_000, message = "El salario base debe ser menor o igual a 15.000.000(quince millones)")
        Long baseSalary
){}
