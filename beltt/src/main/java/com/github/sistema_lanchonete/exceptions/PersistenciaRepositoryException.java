package com.github.sistema_lanchonete.exceptions;

public class PersistenciaRepositoryException extends RuntimeException{
    public PersistenciaRepositoryException(String message) {
        super(message);
    }
    public PersistenciaRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
