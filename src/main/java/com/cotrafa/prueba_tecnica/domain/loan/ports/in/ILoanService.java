package com.cotrafa.prueba_tecnica.domain.loan.ports.in;

import com.cotrafa.prueba_tecnica.domain.loan.Loan;

public interface ILoanService {
    Loan createOne(Loan loan);
}
