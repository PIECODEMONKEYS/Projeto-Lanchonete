package com.github.sistema_lanchonete.entity;

import jakarta.persistence.*;
import java.util.List;
import com.github.sistema_lanchonete.entity.Produtos;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Table (name = "pedidos")
public class Pedidos {
    @Id
    @GeneratedValue(strategy =   GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_hora", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dataHora;

    @Column(name = "valor_total", precision = 10, scale = 2, nullable = false)
    private BigDecimal valorTotal;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    /*@JoinTable(
            name = "pedidos_produtos",
            joinColumns = @JoinColumn(name = "fk_pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_produto_id")
    )*/
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedidos(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }
}
