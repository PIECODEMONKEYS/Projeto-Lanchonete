package com.github.sistema_lanchonete.service.metodo;

import java.math.BigDecimal;

public interface MetodoPagamentoStrategy {
    BigDecimal calcular(BigDecimal valor);
}
