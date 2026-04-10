package com.github.sistema_lanchonete.controller;

import com.github.sistema_lanchonete.entity.Produtos;
import com.github.sistema_lanchonete.repositories.ProdutosRepository;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.Scanner;
public class ProdutosController {
    private final ProdutosRepository repository;
    public ProdutosController(ProdutosRepository repository) {
        this.repository = repository;
    }

    public void adicionarItem(Scanner sc, EntityManager em, Produtos produtos)
    {
        try{
            //noinspection GrazieInspectionRunner
            System.out.println("Digite o nome do produto");
            String nome = leitoresController.lerString(sc);
            produtos.setNome(nome);

            //noinspection GrazieInspectionRunner
            System.out.println("Digite o valor do produto");
            double valor = leitoresController.lerDouble(sc);
            produtos.setPreco(BigDecimal.valueOf(valor));

            this.repository.create(produtos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
