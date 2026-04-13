package com.github.sistema_lanchonete.DTO;

public class PagamentoDTO {
    private Long pedidoId;
    private String metodoPagamento;

    public PagamentoDTO() {
    }

    public PagamentoDTO(Long pedidoId, String metodoPagamento) {
        this.pedidoId = pedidoId;
        this.metodoPagamento = metodoPagamento;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }
}