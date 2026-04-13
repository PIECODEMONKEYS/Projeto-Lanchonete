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

    private int quantidade;

    // Métodos necessários:
    public void setPedido(Pedidos pedido) { this.pedido = pedido; }
    public void setProduto(Produtos produto) { this.produto = produto; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public ItemPedido(){}
    public Pedidos getPedido() { return pedido; }
    public Produtos getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
}