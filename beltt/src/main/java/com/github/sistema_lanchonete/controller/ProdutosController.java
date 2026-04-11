package com.github.sistema_lanchonete.controller;

import com.github.sistema_lanchonete.entity.Produtos;
import com.github.sistema_lanchonete.repositories.ProdutosRepository;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProdutosController {
    private final ProdutosRepository repository;

    public ProdutosController(ProdutosRepository repository) {
        this.repository = repository;
    }

    public void exibirMenuProdutos() {
        int op;
        do {
            System.out.println("\n🍔 --- GESTÃO DE CARDÁPIO ---");
            System.out.println("1. Listar | 2. Novo | 3. Atualizar | 4. Remover | 5. Voltar");
            op = LeitoresController.lerInteiro("Escolha uma opção: ");

            switch (op) {
                case 1 -> listarTodos();
                case 2 -> adicionarItem(null, new Produtos());
                case 3 -> {
                    int id = LeitoresController.lerInteiro("ID do produto para atualizar: ");
                    Produtos p = repository.findById(id);
                    if (p != null) atualizarItem(null, p);
                    else System.out.println("❌ Produto não encontrado.");
                }
                case 4 -> deletarItem();
            }
        } while (op != 5);
    }

    private void listarTodos() {
        var lista = repository.buscarTodos();
        System.out.println("\n--- LISTA DE PRODUTOS ---");
        lista.forEach(p -> System.out.printf("ID: %d | %-20s | R$ %.2f%n", p.getId(), p.getNome(), p.getPreco()));
    }

    public void adicionarItem(EntityManager em, Produtos p) {
        String nome = LeitoresController.lerString("Nome do produto: ");
        double valor = LeitoresController.lerDouble("Preço: ");
        p.setNome(nome);
        p.setPreco(BigDecimal.valueOf(valor));
        repository.salvar(p);
        System.out.println("✅ Produto salvo!");
    }

    public void atualizarItem(EntityManager em, Produtos p) {
        String nome = LeitoresController.lerString("Novo nome: ");
        double valor = LeitoresController.lerDouble("Novo preço: ");
        p.setNome(nome);
        p.setPreco(BigDecimal.valueOf(valor));
        repository.salvar(p);
        System.out.println("✅ Produto atualizado!");
    }

    public void deletarItem() {
        int id = LeitoresController.lerInteiro("ID para remover: ");
        Produtos p = repository.findById(id);
        if (p != null) {
            repository.delete(p);
            System.out.println("✅ Produto removido!");
        } else {
            System.out.println("❌ Não encontrado.");
        }
    }
}