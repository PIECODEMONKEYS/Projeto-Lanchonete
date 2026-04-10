package com.github.sistema_lanchonete.service.metodo;

public class PagamentoCredito implements MetodoPagamento{
    @Override
    public double calcular(double valor){
        return valor * 1.05;
    }
}
