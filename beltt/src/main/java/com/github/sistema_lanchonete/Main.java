package com.github.sistema_lanchonete;


import com.github.sistema_lanchonete.config.CustomizerFactory;
import com.github.sistema_lanchonete.entity.Cardapio;
import com.github.sistema_lanchonete.repositories.CardapioRepository;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        EntityManager em = CustomizerFactory.getEntityManager();

        CardapioRepository cardapioRepository = new CardapioRepository(em);

        Cardapio cardapio = new Cardapio();

        cardapio.setNome("x-bagunca");
        cardapio.setPreco(BigDecimal.valueOf(30.00));
        cardapioRepository.create(cardapio);

        var p2 = cardapioRepository.findById(1L);
        System.out.println(p2);

        cardapioRepository.findAll().stream().forEach(System.out::println);

       /* var p3 = cardapioRepository.findById(1L);
        cardapio.setNome("x-bagun");
    */
        em.close();
        CustomizerFactory.fechar();
    }

}
