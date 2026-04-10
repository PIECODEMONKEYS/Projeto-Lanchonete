package com.github.sistema_lanchonete.exceptions;

public class PersistenciaIngredienteRepositoryException extends RuntimeException {
    public PersistenciaIngredienteRepositoryException(String message) {
        super(message);
    }

    public PersistenciaIngredienteRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
