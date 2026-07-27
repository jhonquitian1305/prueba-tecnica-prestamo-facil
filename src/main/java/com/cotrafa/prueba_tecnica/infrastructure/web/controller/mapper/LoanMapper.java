package com.cotrafa.prueba_tecnica.infrastructure.web.controller.mapper;

import com.cotrafa.prueba_tecnica.domain.loan.Loan;
import com.cotrafa.prueba_tecnica.domain.loan.builder.LoanBuilder;
import com.cotrafa.prueba_tecnica.infrastructure.web.controller.dto.LoanDTO;
import com.cotrafa.prueba_tecnica.infrastructure.web.controller.dto.LoanCreatedResponse;

public class LoanMapper {

    public static Loan toModel(LoanDTO loanDTO){
        return new LoanBuilder.Builder()
                .amount(loanDTO.amount())
                .termMonths(loanDTO.termMonths())
                .userId(loanDTO.userId())
                .idLoanType(loanDTO.idLoanType())
                .build();
    }

    public static LoanCreatedResponse toResponse(Loan loan){
        return LoanCreatedResponse.builder()
                .id(loan.getId())
                .amount(loan.getAmount())
                .termMonths(loan.getTermMonths())
                .userId(loan.getUserId())
                .idLoanType(loan.getIdLoanType())
                .idState(loan.getIdState())
                .build();
    }
}
