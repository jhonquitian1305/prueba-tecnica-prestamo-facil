package com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.mapper;

import com.cotrafa.prueba_tecnica.domain.loan.Loan;
import com.cotrafa.prueba_tecnica.domain.loan.builder.LoanBuilder;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.entity.UserEntity;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.entity.LoanEntity;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.entity.LoanStateEntity;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.entity.LoanTypeEntity;

public class LoanMapperJpa {

    public static LoanEntity toEntity(Loan loan){
        return LoanEntity.builder()
                .amount(loan.getAmount())
                .termMonths(loan.getTermMonths())
                .user(UserEntity.builder().id(loan.getUserId()).build())
                .loanType(LoanTypeEntity.builder().id(loan.getIdLoanType()).build())
                .loanState(LoanStateEntity.builder().id(loan.getIdLoanType()).build())
                .build();
    }

    public static Loan toModel(LoanEntity loanEntity){
        return new LoanBuilder.Builder()
                .id(loanEntity.getId())
                .amount(loanEntity.getAmount())
                .termMonths(loanEntity.getTermMonths())
                .userId(loanEntity.getUser().getId())
                .idLoanType(loanEntity.getLoanType().getId())
                .idState(loanEntity.getLoanState().getId())
                .build();
    }
}
