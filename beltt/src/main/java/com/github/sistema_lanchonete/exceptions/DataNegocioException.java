package com.github.sistema_lanchonete.exceptions;

public class DataNegocioException extends RuntimeException {
    public DataNegocioException(String message) {
        super(message);
    }

    public DataNegocioException(String message, Throwable cause){
        super(message, cause);
    }
}
