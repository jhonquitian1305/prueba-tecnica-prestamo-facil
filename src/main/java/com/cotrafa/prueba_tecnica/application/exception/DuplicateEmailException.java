package com.cotrafa.prueba_tecnica.application.exception;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String email) {
        super("El email %s ya se encuentra registrado ".formatted(email));
    }
}
