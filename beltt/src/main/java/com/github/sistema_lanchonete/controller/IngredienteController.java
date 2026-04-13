package com.github.sistema_lanchonete.controller;

import com.github.sistema_lanchonete.config.CustomizerFactory;
import com.github.sistema_lanchonete.entity.Ingrediente;
import com.github.sistema_lanchonete.repositories.IngredienteRepository;

// VERIFIQUE ESTA LINHA: deve ter o "e" em Ingrediente
public class IngredienteController {

    private final IngredienteRepository repository;

    public IngredienteController() {
        this.repository = new IngredienteRepository(CustomizerFactory.getEntityManager());
    }

    public void exibirMenuEstoque() {
        boolean noEstoque = true;
        while (noEstoque) {
            System.out.println("\n--- GERENCIAMENTO DE ESTOQUE (INGREDIENTES) ---");
            System.out.println("1. Listar Estoque Atual");
            System.out.println("2. Cadastrar Novo Ingrediente");
            System.out.println("3. Voltar ao Menu Principal");

            int escolha = LeitoresController.lerInteiro("Escolha uma opção: ");

            switch (escolha) {
                case 1: listarIngredientes(); break;
                case 2: cadastrarIngrediente(); break;
                case 3: noEstoque = false; break;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    private void listarIngredientes() {
        System.out.println("\n--- CONSULTANDO BANCO DE DADOS ---");
        try {
            List<Ingrediente> lista = repository.findAll();
            if (lista.isEmpty()) {
                System.out.println("O estoque está vazio.");
            } else {
                System.out.printf("%-5s | %-20s | %-10s%n", "ID", "NOME", "QUANTIDADE");
                for (Ingrediente i : lista) {
                    System.out.printf("%-5d | %-20s | %-10d%n", i.getId(), i.getNome(), i.getEstoque());
                }
            }
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    private void cadastrarIngrediente() {
        String nome = LeitoresController.lerString("Nome: ");
        int qtd = LeitoresController.lerInteiro("Qtd: ");
        try {
            Ingrediente novo = new Ingrediente();
            novo.setNome(nome);
            novo.setEstoque(qtd);
            repository.create(novo);
            System.out.println("✅ Cadastrado!");
        } catch (Exception e) {
            System.err.println("❌ Erro: " + e.getMessage());
        }
    }
}