package com.github.sistema_lanchonete;


import com.github.sistema_lanchonete.config.CustomizerFactory;
import com.github.sistema_lanchonete.entity.Produtos;
import com.github.sistema_lanchonete.repositories.ProdutosRepository;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;

public class Main {
    @SuppressWarnings("GrazieInspectionRunner")
    public static void main(String[] args) {
        EntityManager em = CustomizerFactory.getEntityManager();
        //vou deletar essa main basicametnet
        ProdutosRepository produtosRepository = new ProdutosRepository(em);

        Produtos produtos = new Produtos();

        //noinspection GrazieInspectionRunner
        produtos.setNome("x-bagunca");
        produtos.setPreco(BigDecimal.valueOf(30.00));
        produtosRepository.create(produtos);

        var p2 = produtosRepository.findById(1L);
        System.out.println(p2);

        produtosRepository.findAll().stream().forEach(System.out::println);

       /* var p3 = cardapioRepository.findById(1L);
        cardapio.setNome("x-bagun");
    */
        em.close();
        CustomizerFactory.fechar();
    }

}
