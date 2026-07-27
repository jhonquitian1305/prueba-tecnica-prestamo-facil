package com.cotrafa.prueba_tecnica.domain.loan.builder;

import com.cotrafa.prueba_tecnica.domain.loan.LoanType;

import java.math.BigDecimal;

public class LoanTypeBuilder {

    public static class Builder {
        private Long id;
        private String name;
        private BigDecimal interestRate;

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder interestRate(BigDecimal interestRate){
            this.interestRate = interestRate;
            return this;
        }

        public LoanType build(){
            return new LoanType(id, name, interestRate);
        }
    }
}
