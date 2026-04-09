package com.github.codemonkeys.service.metodo;

public class pagamentoPix implements MetodoPagamento{
    @Override
    public double calcular(double valor){
        return valor;
    }
}
