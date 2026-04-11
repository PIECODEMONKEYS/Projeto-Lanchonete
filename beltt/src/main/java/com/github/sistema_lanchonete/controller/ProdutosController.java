package com.github.sistema_lanchonete.controller;

import com.github.sistema_lanchonete.entity.Produtos;
import com.github.sistema_lanchonete.exceptions.PersistenciaProdutoRepositoryException;
import com.github.sistema_lanchonete.exceptions.RegraNegocioException;
import com.github.sistema_lanchonete.repositories.ProdutosRepository;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;

public class ProdutosController {
    private final ProdutosRepository repository;

    public ProdutosController(ProdutosRepository repository) {
        this.repository = repository;
    }

    public void adicionarItem(EntityManager em, Produtos produtos) {
        try {
            // A mensagem agora é passada diretamente para o método do LeitoresController
            String nome = LeitoresController.lerString("Digite o nome do produto: ");
            if (nome == null || nome.isBlank()) {
                throw new RegraNegocioException("Nome nao pode ser vazio");
            }
            produtos.setNome(nome);

            double valor = LeitoresController.lerDouble("Digite o valor do produto: ");
            if (valor <= 0) {
                throw new RegraNegocioException("Valor não pode ser zero nem negativo");
            }
            produtos.setPreco(BigDecimal.valueOf(valor));

            this.repository.create(produtos);
            System.out.println("Produto criado com sucesso!");
        } catch (RegraNegocioException e) {
            System.out.println("Erro de Negócio: " + e.getMessage());
        } catch (PersistenciaProdutoRepositoryException e) {
            System.out.println("Erro ao enviar dados: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado no sistema: " + e);
        }
    }

    public void atualizarItem(EntityManager em, Produtos produtos) {
        try {
            String nome = LeitoresController.lerString("Digite o novo nome do produto: ");
            if (nome == null || nome.isBlank()) {
                throw new RegraNegocioException("Nome nao pode ser vazio");
            }
            produtos.setNome(nome);

            double valor = LeitoresController.lerDouble("Digite o novo valor do produto: ");
            if (valor <= 0) {
                throw new RegraNegocioException("Valor não pode ser zero nem negativo");
            }
            produtos.setPreco(BigDecimal.valueOf(valor));

            this.repository.update(produtos);
            System.out.println("Produto atualizado com sucesso!");
        } catch (RegraNegocioException e) {
            System.out.println("Erro de Negócio: " + e.getMessage());
        } catch (PersistenciaProdutoRepositoryException e) {
            System.out.println("Erro ao atualizar dados: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado no sistema: " + e);
        }
    }

    public void deletarItem() {
        try {
            // Corrigido de leitorInteger para lerInteiro conforme sua LeitoresController
            Integer id = LeitoresController.lerInteiro("Digite o id do produto que deseja remover: ");

            Produtos produto = this.repository.findById(id);
            if (produto == null) {
                throw new RegraNegocioException("Operação cancelada: ID inválido");
            }

            String confirmacao = LeitoresController.lerString("Deseja realmente deletar o item? Digite SIM para confirmar: ");
            if (confirmacao.equalsIgnoreCase("SIM")) {
                this.repository.delete(produto);
                System.out.println("Item deletado com sucesso!");
            } else {
                System.out.println("Operação cancelada.");
            }
        } catch (RegraNegocioException e) {
            System.out.println("Aviso: " + e.getMessage());
        } catch (PersistenciaProdutoRepositoryException e) {
            System.out.println("Erro crítico na conexão com o banco de dados: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Erro inesperado ao remover item: " + e);
        }
    }
}