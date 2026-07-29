package com.cotrafa.prueba_tecnica.infrastructure.web.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Solicitud para crear un préstamo")
public record LoanDTO(

        @Schema(description = "Monto solicitado", example = "5000000")
        @Positive(message = "La cantidad debe ser mayor a cero")
        @NotNull(message = "La cantidad es obligatoria")
        Long amount,

        @Schema(description = "Plazo en meses", example = "36")
        @Positive(message = "El plazo en meses debe ser mayor a cero")
        @NotNull(message = "El plazo en meses es obligatorio")
        Integer termMonths,

        @Schema(description = "Identificador del usuario", example = "1")
        @NotEmpty(message = "El id del usuario es obligatorio")
        String userId,

        @Schema(description = "Tipo de préstamo", example = "1")
        @NotNull(message = "El id del tipo de préstamo es obligatorio")
        @Positive(message = "Debe ser un id válido")
        Long idLoanType
) {
}
