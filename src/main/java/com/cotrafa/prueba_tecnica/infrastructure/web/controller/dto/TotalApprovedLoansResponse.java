package com.cotrafa.prueba_tecnica.infrastructure.web.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Respuesta con el valor total de los préstamos aprobados")
public record TotalApprovedLoansResponse(

        @Schema(
                description = "Valor total de los préstamos aprobados",
                example = "12500000.50"
        )
        BigDecimal totalApprovedAmount
) {
}