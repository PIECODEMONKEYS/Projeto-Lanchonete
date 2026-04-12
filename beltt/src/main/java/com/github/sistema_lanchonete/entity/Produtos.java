package com.github.sistema_lanchonete.entity;


import jakarta.persistence.*;
import java.math.BigDecimal;


@SuppressWarnings("GrazieInspectionRunner")
@Entity
@Table(name = "produtos")
public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal preco;

    public Produtos() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        // Converte o BigDecimal para double para ser compatível com o retorno do método
        return preco != null ? preco.doubleValue() : 0.0;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }


}
