package com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.mapper;

import com.cotrafa.prueba_tecnica.application.dto.LoanResponse;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.entity.LoanEntity;

public class LoanResponseMapper {

    public static LoanResponse toResponse(LoanEntity loanEntity){
        return LoanResponse.builder()
                .id(loanEntity.getId())
                .amount(loanEntity.getAmount())
                .termMonths(loanEntity.getTermMonths())
                .applicantName(loanEntity.getUser().getName())
                .applicantEmail(loanEntity.getUser().getEmail())
                .baseSalary(loanEntity.getUser().getBaseSalary())
                .loanType(loanEntity.getLoanType().getName())
                .interestRate(loanEntity.getLoanType().getInterestRate())
                .loanState(loanEntity.getLoanState().getName())
                .build();
    }
}
