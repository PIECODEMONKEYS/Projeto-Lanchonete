package com.github.sistema_lanchonete.service.metodo;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PagamentoCredito implements MetodoPagamentoStrategy {

    @Override
    public BigDecimal calcular(BigDecimal valor) {
        return valor.multiply(new BigDecimal("1.05"))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
