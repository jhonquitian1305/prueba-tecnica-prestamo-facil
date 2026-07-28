package com.cotrafa.prueba_tecnica.domain.loan.ports.out;

import com.cotrafa.prueba_tecnica.application.dto.LoanValidationResult;

public interface LoanProcedureRepositoryPort {
    LoanValidationResult evaluateAutomatic(Long loanId);
}
