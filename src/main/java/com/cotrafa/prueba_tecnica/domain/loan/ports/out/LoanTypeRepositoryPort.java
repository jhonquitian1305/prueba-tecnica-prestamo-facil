package com.cotrafa.prueba_tecnica.domain.loan.ports.out;

import com.cotrafa.prueba_tecnica.domain.loan.LoanType;

import java.util.Optional;

public interface LoanTypeRepositoryPort {
    Optional<LoanType> findById(Long idLoanType);
}
