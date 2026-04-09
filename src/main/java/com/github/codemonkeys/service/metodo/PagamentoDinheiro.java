package com.github.codemonkeys.service.metodo;

public class PagamentoDinheiro implements MetodoPagamento{
    @Override
    public double calcular(double valor) {
        return valor;
    }
}
