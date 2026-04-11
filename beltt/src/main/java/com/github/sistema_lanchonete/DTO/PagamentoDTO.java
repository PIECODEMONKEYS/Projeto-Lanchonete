package com.github.sistema_lanchonete.DTO;

import java.math.BigDecimal;

public class PagamentoDTO {
    private BigDecimal valorOriginal;
    private String metodoPagamento;

    public PagamentoDTO(){

    }

    public PagamentoDTO(BigDecimal valorOriginal, String metodoPagamento){
        this.valorOriginal = valorOriginal;
        this.metodoPagamento = metodoPagamento;
    }

    public BigDecimal getValorOriginal(){
        return valorOriginal;
    }

    public String getMetodoPagamento(){
        return metodoPagamento;
    }

    public void setValorOriginal(BigDecimal valorOriginal){
        this.valorOriginal = valorOriginal;
    }

    public void setMetodoPagamento(String metodoPagamento){
        this.metodoPagamento = metodoPagamento;
    }
}
