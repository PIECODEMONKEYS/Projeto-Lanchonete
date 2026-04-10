package com.github.sistema_lanchonete.exceptions;

public class PagamentoIncorretoException extends RuntimeException {
    public PagamentoIncorretoException(String message) {
        super(message);
    }
}
