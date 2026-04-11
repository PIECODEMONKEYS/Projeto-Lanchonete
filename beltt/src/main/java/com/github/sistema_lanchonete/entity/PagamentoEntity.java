package com.github.sistema_lanchonete.entity;

import com.github.sistema_lanchonete.service.metodo.MetodoPagamento;
import com.github.sistema_lanchonete.service.metodo.StatusPagamento;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamento")
public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento")
    private Long idPagamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MetodoPagamento metodo;

    @Column(name = "valor_original", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorOriginal;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal taxa;

    @Column(name = "valor_final", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorFinal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPagamento status;

    @Column(name = "data_pagamento")
    private LocalDateTime dataPagamento;

    public PagamentoEntity() {
    }

    public Long getIdPagamento() {
        return idPagamento;
    }

    public MetodoPagamento getMetodo() {
        return metodo;
    }

    public void setMetodo(MetodoPagamento metodo) {
        this.metodo = metodo;
    }

    public BigDecimal getValorOriginal() {
        return valorOriginal;
    }

    public void setValorOriginal(BigDecimal valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}