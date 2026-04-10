package com.github.sistema_lanchonete.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "ingredientes")
public class IngredienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer estoque;

    public IngredienteEntity() {}

    public IngredienteEntity(String nome, Integer estoque) {
        this.nome = nome;
        this.estoque = estoque;
    }
}