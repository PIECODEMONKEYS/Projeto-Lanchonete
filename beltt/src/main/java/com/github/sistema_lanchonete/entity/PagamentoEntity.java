package com.github.sistema_lanchonete.entity;

import com.github.sistema_lanchonete.service.metodo.MetodoPagamento;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamento")
public class PagamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPagamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MetodoPagamentoEnum metodo;

    @Column(name = "valor_original", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorOriginal;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal taxa;

    @Column(name = "valor_final", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorFinal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPagamentoEnum status;

    @Column(name = "data_pagamento")
    private LocalDateTime dataPagamento;

    public PagamentoEntity(){

    }

    public Long getIdPagamento(){
        return idPagamento;
    }

    public void setMetodo(MetodoPagamentoEnum metodo){
        this.metodo = metodo;
    }

    public MetodoPagamentoEnum getMetodo(){
        return metodo;
    }

    public enum MetodoPagamentoEnum{
        CREDITO,
        DEBITO,
        PIX,
        DINHEIRO
    }

    public enum StatusPagamentoEnum{
        PEDENTE,
        APROVADO,
        RECUSADO,
        CANCELADO
    }
}
