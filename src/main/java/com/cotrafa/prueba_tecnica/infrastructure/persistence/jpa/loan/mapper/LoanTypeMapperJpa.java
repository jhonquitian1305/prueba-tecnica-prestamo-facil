package com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.mapper;

import com.cotrafa.prueba_tecnica.domain.loan.LoanType;
import com.cotrafa.prueba_tecnica.domain.loan.builder.LoanTypeBuilder;
import com.cotrafa.prueba_tecnica.infrastructure.persistence.jpa.loan.entity.LoanTypeEntity;

public class LoanTypeMapperJpa {

    public static LoanType toModel(LoanTypeEntity loanTypeEntity){
        return new LoanTypeBuilder.Builder()
                .id(loanTypeEntity.getId())
                .name(loanTypeEntity.getName())
                .interestRate(loanTypeEntity.getInterestRate())
                .automaticValidation(loanTypeEntity.isAutomaticValidation())
                .build();
    }
}
