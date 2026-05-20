package com.example.ejercicio12.exceptions;

public class ApplicationNotFoundException extends RuntimeException {
    public ApplicationNotFoundException(Long id) {
        super("Application not found with id: " + id);
    }
}
