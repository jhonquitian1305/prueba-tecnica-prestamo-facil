package com.cotrafa.prueba_tecnica.application.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record LoanResponse(
        Long id,
        Long amount,
        Integer termMonths,
        String applicantName,
        String applicantEmail,
        Long baseSalary,
        String loanType,
        BigDecimal interestRate,
        String loanState
) {}
