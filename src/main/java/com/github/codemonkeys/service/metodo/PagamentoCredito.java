package com.github.codemonkeys.service.metodo;

public class PagamentoCredito implements MetodoPagamento{
    @Override
    public double calcular(double valor){
        return valor * 1.05;
    }
}
