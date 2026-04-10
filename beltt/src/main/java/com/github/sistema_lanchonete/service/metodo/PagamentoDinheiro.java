package com.github.sistema_lanchonete.service.metodo;

public class PagamentoDinheiro implements MetodoPagamento{
    @Override
    public double calcular(double valor) {
        return valor;
    }
}
