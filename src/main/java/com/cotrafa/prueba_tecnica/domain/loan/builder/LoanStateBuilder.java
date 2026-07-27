package com.cotrafa.prueba_tecnica.domain.loan.builder;

import com.cotrafa.prueba_tecnica.domain.loan.LoanState;

public class LoanStateBuilder {
    public static class Builder{
        private Long id;
        private String name;

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public LoanState build(){
            return new LoanState(id, name);
        }
    }
}
