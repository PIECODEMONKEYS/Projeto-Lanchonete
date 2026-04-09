package com.github.codemonkeys.DTO;

public class PagamentoDTO {
    private double valor;
    private String metodoPagamento;
    public PagamentoDTO(double valor, String metodoPagamento){
        this.valor = valor;
        this.metodoPagamento = metodoPagamento;
    }

    public double getValor(){
        return valor;
    }

    public String getMetodoPagamento(){
        return metodoPagamento;
    }

    public void setValor(double valor){
        this.valor = valor;
    }

    public void setMetodoPagamento(String metodoPagamento){
        this.metodoPagamento = metodoPagamento;
    }
}
