package com.github.sistema_lanchonete.service.metodo;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PagamentoDebito implements MetodoPagamentoStrategy {

    @Override
    public BigDecimal calcular(BigDecimal valor) {
        return valor.multiply(new BigDecimal("1.02"))
                .setScale(2, RoundingMode.HALF_UP);
    }
}