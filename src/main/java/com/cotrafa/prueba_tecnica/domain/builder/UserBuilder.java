package com.cotrafa.prueba_tecnica.domain.builder;

import com.cotrafa.prueba_tecnica.domain.User;

public class UserBuilder {

    public static class Builder {
        private String id;
        private String name;
        private String lastname;
        private String email;
        private String typeIdentification;
        private String identification;
        private Long baseSalary;

        public Builder id(String id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder lastname(String lastname){
            this.lastname = lastname;
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public Builder typeIdentification(String typeIdentification){
            this.typeIdentification = typeIdentification;
            return this;
        }

        public Builder identification(String identification){
            this.identification = identification;
            return this;
        }

        public Builder baseSalary(Long baseSalary){
            this.baseSalary = baseSalary;
            return this;
        }

        public User build(){
            return new User(id, name, lastname, email, typeIdentification, identification, baseSalary);
        }

    }
}
