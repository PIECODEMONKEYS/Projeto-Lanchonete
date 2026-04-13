package com.github.sistema_lanchonete.exceptions;

public class AcharIngredienteException extends RuntimeException {
    public AcharIngredienteException(String message) {
        super(message);
    }
    public AcharIngredienteException(String message, Throwable cause) {
        super(message, cause);
    }
}
