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

    //esse aqui precisa por mais pra frente por enquanto deixar assim
    //private String frequencia;
    public IngredienteEntity() {}

    public IngredienteEntity(String nome, Integer estoque) {
        this.nome = nome;
        this.estoque = estoque;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Integer getEstoque() { return estoque; }
    public void setEstoque(Integer estoque) { this.estoque = estoque; }
/* enquanto nao tem no banco de dados deixar comentado
    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }*/
}