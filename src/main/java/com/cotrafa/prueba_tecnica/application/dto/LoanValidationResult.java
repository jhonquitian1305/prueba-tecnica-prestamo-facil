package com.cotrafa.prueba_tecnica.application.dto;

import java.math.BigDecimal;

public record LoanValidationResult(
        Long state,
        BigDecimal monthlyPayment
) {
}
