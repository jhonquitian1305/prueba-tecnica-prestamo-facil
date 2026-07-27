package com.cotrafa.prueba_tecnica.infrastructure.web.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
public record UserResponse(
        String id,
        String name,
        String lastname,
        String email,
        String typeIdentification,
        String identification,
        Long baseSalary
){}
