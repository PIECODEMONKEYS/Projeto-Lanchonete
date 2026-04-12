package com.github.sistema_lanchonete.service.metodo;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PagamentoDinheiro implements MetodoPagamentoStrategy {

    @Override
    public BigDecimal calcular(BigDecimal valor) {
        return valor.setScale(2, RoundingMode.HALF_UP);
    }
}