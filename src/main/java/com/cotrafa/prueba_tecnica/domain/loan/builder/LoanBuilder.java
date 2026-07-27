package com.cotrafa.prueba_tecnica.domain.loan.builder;

import com.cotrafa.prueba_tecnica.domain.loan.Loan;

public class LoanBuilder {
    public static class Builder{
        private Long id;
        private Long amount;
        private int termMonths;
        private String userId;
        private Long idLoanType;
        private Long idState;

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder amount(Long amount){
            this.amount = amount;
            return this;
        }

        public Builder termMonths(int termMonths){
            this.termMonths = termMonths;
            return this;
        }

        public Builder userId(String userId){
            this.userId = userId;
            return this;
        }

        public Builder idLoanType(Long idLoanType){
            this.idLoanType = idLoanType;
            return this;
        }

        public Builder idState(Long idState){
            this.idState = idState;
            return this;
        }

        public Loan build(){
            return new Loan(id, amount, termMonths, userId, idLoanType, idState);
        }
    }
}
