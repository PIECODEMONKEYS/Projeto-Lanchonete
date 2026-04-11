package com.github.sistema_lanchonete.DTO;

import java.math.BigDecimal;

public class PagamentoDTO {
    private double valorOriginal;
    private String metodoPagamento;

    public PagamentoDTO(){

    }

    public PagamentoDTO(double valor, String metodoPagamento){
        this.valorOriginal = valorOriginal;
        this.metodoPagamento = metodoPagamento;
    }

    public double getValorOriginal(){
        return valorOriginal;
    }

    public String getMetodoPagamento(){
        return metodoPagamento;
    }

    public void setValorOriginal(double valorOriginal){
        this.valorOriginal = valorOriginal;
    }

    public void setMetodoPagamento(String metodoPagamento){
        this.metodoPagamento = metodoPagamento;
    }
}
