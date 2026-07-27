package com.cotrafa.prueba_tecnica.infrastructure.web.controller.dto;

import lombok.Builder;

@Builder
public record LoanCreatedResponse(
        Long id,
        Long amount,
        int termMonths,
        String userId,
        Long idLoanType,
        Long idState
) {
}
