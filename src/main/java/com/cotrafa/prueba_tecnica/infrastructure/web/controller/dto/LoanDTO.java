package com.cotrafa.prueba_tecnica.infrastructure.web.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record LoanDTO(
        @Positive(message = "La cantidad debe ser mayor a cero")
        @NotNull(message = "La cantidad es obligatoria")
        Long amount,

        @Positive(message = "El plazo en meses debe ser mayor a cero")
        @NotNull(message = "El plazo en meses es obligatorio")
        Integer termMonths,

        @NotEmpty(message = "El id del usuario es obligatorio")
        String userId,

        @NotNull(message = "El id del tipo de préstamo es obligatorio")
        @Positive(message = "Debe ser un id válido")
        Long idLoanType
) {
}
