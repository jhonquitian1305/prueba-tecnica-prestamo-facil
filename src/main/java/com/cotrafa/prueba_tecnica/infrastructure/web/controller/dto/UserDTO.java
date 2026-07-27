package com.cotrafa.prueba_tecnica.infrastructure.web.controller.dto;

import jakarta.validation.constraints.*;

public record UserDTO(
        @NotEmpty(message = "El nombre es obligatorio")
        String name,

        @NotEmpty(message = "Los apellidos son obligatorios")
        String lastname,

        @NotEmpty(message = "El email es obligatorio")
        @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$", message = "Debe ser un email válido")
        String email,

        @NotEmpty(message = "El tipo de identificacion es obligatorio")
        String typeIdentification,

        @NotEmpty(message = "El número de identificación es obligatorio")
        String identification,

        @NotNull(message = "El salario base es obligatorio")
        @Min(value = 0, message = "El salario base debe ser mayor o igual a cero")
        @Max(value = 15_000_000, message = "El salario base debe ser menor o igual a 15.000.000(quince millones)")
        Long baseSalary
){}
