package com.github.sistema_lanchonete.exceptions;

public class PersistenciaProdutoRepositoryException extends RuntimeException{
    public PersistenciaProdutoRepositoryException(String message) {
        super(message);
    }
    public PersistenciaProdutoRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
