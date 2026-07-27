package com.cotrafa.prueba_tecnica.domain.loan.ports.out;

import com.cotrafa.prueba_tecnica.domain.loan.Loan;

public interface LoanRepositoryPort {
    Loan createOne(Loan loan);
}
