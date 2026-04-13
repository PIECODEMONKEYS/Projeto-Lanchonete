package com.github.sistema_lanchonete.exceptions;

public class AcharProdutoException extends RuntimeException {
    public AcharProdutoException(String message) {
        super(message);
    }
    public AcharProdutoException(String message, Throwable cause) {
    }
}
