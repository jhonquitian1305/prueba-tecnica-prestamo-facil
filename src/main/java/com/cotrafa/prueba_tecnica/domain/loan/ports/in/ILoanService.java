package com.cotrafa.prueba_tecnica.domain.loan.ports.in;

import com.cotrafa.prueba_tecnica.application.dto.LoanResponse;
import com.cotrafa.prueba_tecnica.application.dto.PageResponseDTO;
import com.cotrafa.prueba_tecnica.domain.loan.Loan;

public interface ILoanService {
    Loan createOne(Loan loan);
    PageResponseDTO<LoanResponse> getAll(Long loanStateId, int page, int size);
}
