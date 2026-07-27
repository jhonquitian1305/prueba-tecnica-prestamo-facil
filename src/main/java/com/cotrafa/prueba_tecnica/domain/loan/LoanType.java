package com.cotrafa.prueba_tecnica.domain.loan;

import java.math.BigDecimal;

public class LoanType {
    private Long id;
    private String name;
    private BigDecimal interestRate;

    public LoanType(Long id, String name, BigDecimal interestRate) {
        this.id = id;
        this.name = name;
        this.interestRate = interestRate;
    }

    public LoanType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
