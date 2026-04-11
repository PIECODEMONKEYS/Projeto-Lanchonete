package com.github.sistema_lanchonete.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valorTotal;
    private LocalDateTime dataPedido = LocalDateTime.now();

    // Getters e Setters rápidos
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }
    public LocalDateTime getDataPedido() { return dataPedido; }
}