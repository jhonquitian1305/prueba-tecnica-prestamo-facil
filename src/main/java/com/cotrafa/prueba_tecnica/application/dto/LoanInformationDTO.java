package com.cotrafa.prueba_tecnica.application.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record LoanInformationDTO(
        Long id,
        Long amount,
        BigDecimal interestRate,
        int termMonth,
        String emailUser
) {
}
