package com.github.sistema_lanchonete.exceptions;

public class PersistencePedidosException extends RuntimeException {
    public PersistencePedidosException(String message) {
        super(message);
    }

    public PersistencePedidosException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
