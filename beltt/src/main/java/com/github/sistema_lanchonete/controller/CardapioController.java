package com.github.sistema_lanchonete.controller;

import com.github.sistema_lanchonete.repositories.CardapioRepository;
import java.util.Scanner;
public class CardapioController {
    private final CardapioRepository repository;
    public CardapioController(CardapioRepository repository) {
        this.repository = repository;
    }

    public void adicionarItem(Scanner sc)
    {
        try{
            System.out.println("Digite o nome do produto");
            String nome = leitoresController.lerString(sc);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
