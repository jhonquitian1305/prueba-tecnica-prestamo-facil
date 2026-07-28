package com.cotrafa.prueba_tecnica.domain.loan.ports.out;

import com.cotrafa.prueba_tecnica.application.dto.LoanInformationDTO;
import com.cotrafa.prueba_tecnica.application.dto.LoanResponse;
import com.cotrafa.prueba_tecnica.application.dto.PageResponseDTO;
import com.cotrafa.prueba_tecnica.application.dto.UpdateStateDTO;
import com.cotrafa.prueba_tecnica.domain.loan.Loan;

import java.math.BigDecimal;
import java.util.Optional;

public interface LoanRepositoryPort {
    Loan createOne(Loan loan);
    PageResponseDTO<LoanResponse> getAll(Long loanStateId, int page, int size);

    Optional<LoanInformationDTO> getById(Long aLong);

    void update(UpdateStateDTO updateStateDTO);

    BigDecimal getTotalApproved();
}
