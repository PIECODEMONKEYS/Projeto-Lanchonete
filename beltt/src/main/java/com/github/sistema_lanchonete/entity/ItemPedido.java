package com.github.sistema_lanchonete.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pedidos_produtos")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_pedido_id")
    private Pedidos pedido;

    @ManyToOne
    @JoinColumn(name = "fk_produto_id")
    private Produtos produto;

    private Integer quantidade;

    // Métodos necessários:
    public void setPedido(Pedidos pedido) { this.pedido = pedido; }
    public void setProduto(Produtos produto) { this.produto = produto; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public Pedidos getPedido() { return pedido; }
    public Produtos getProduto() { return produto; }
    public Integer getQuantidade() { return quantidade; }
}