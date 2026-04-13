package com.github.sistema_lanchonete.controller;

import com.github.sistema_lanchonete.config.CustomizerFactory;
import com.github.sistema_lanchonete.entity.Ingrediente;
import com.github.sistema_lanchonete.repositories.IngredienteRepository;
import java.util.List;
import java.util.Scanner;

// VERIFIQUE ESTA LINHA: deve ter o "e" em Ingrediente
public class IngredienteController {

    private final IngredienteRepository repository;

    public IngredienteController() {
        this.repository = new IngredienteRepository(CustomizerFactory.getEntityManager());
    }

    public void exibirMenuEstoque(Scanner sc) {
        boolean noEstoque = true;
        while (noEstoque) {
            System.out.println("\n\tGERENCIAMENTO DE ESTOQUE");
            System.out.println("1. Listar Estoque Atual");
            System.out.println("2. Cadastrar ou atualizar Ingrediente");
            System.out.println("3. Remover Ingrediente");//nao tinha no codigo do gui
            System.out.println("4. Voltar ao Menu Principal");

            System.out.println("Escolha uma opção: ");
            int escolha = LeitoresController.lerInteiro(sc);

            switch (escolha) {
                case 1: listarIngredientes(); break;
                case 2: cadastrarIngrediente(sc); break;
                case 3: removerIngrediente(sc); break;
                case 4: noEstoque = false; break;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    private void listarIngredientes() {
        System.out.println("\n\tCONSULTANDO BANCO DE DADOS");
        try {
            List<Ingrediente> lista = repository.buscarTodos();
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
    private void removerIngrediente(Scanner sc) {
        System.out.println("\t1. Mostrar lista de Ingredientes\n" +
                "\t2. Remover ingrediente por id\n" +
                "\t3. Remover ingrediente pelo nome" +
                "\n\t4. Voltar ao Menu Principal");

        boolean ativo = true;
        do{
            int opc = LeitoresController.lerInteiro(sc);
            switch (opc) {
                case 1: listarIngredientes(); break;
                case 2:
                    try {
                        System.out.println("Digite o id do ingrediente: ");
                        int id = LeitoresController.lerInteiro(sc);
                        Ingrediente remove = repository.findById(id);
                        repository.remover(remove);
                        System.out.println("Removido com sucesso!");
                        ativo = false;
                    } catch (Exception e) {
                        System.out.println("Erro ao deletar ingrediente: " + e.getMessage());
                    }
                break;
                case 3:
                    try{
                        System.out.println("Digite o nome exato do ingredient");
                        String nome = LeitoresController.lerString(sc);
                        Ingrediente remove = repository.acharPeloNome(nome);
                        repository.remover(remove);
                        System.out.println("Removido com sucesso!");
                        ativo = false;
                    } catch (Exception e) {
                        System.out.println("Erro ao deletar ingrediente: " + e.getMessage());
                    }
                break;
                case 4: ativo = false; break;
                default:
                    System.out.println("Selecione uma opcao do menu");
                    break;
            }
        }while(ativo);
    }
    private void cadastrarIngrediente(Scanner sc) {
        System.out.println("Nome do ingrediente:");
        String nome = LeitoresController.lerString(sc);
        System.out.println("Quantidade do estoque atual:");
        int qtd = LeitoresController.lerInteiro(sc);
        try {
            Ingrediente novo = new Ingrediente();
            novo.setNome(nome);
            novo.setEstoque(qtd);
            if (repository.existePorNome(nome)) {
                try{
                    repository.atualizar(novo);
                } catch (Exception e) {
                    System.out.println("Erro ao atualizar o ingrediente: " + e.getMessage());
                    return;
                }
            } else {
                repository.salvar(novo);
            }
            System.out.println("Cadastrado!");
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar ingrediente: " + e.getMessage());
        }
    }
    public void incrementarEstoqueSelecionado(Scanner sc) {
        try {
            List<Ingrediente> lista = repository.buscarTodos();

            if (lista.isEmpty()) {
                System.out.println("Não há ingredientes no estoque.");
                return;
            }

            for (Ingrediente i : lista) {
                System.out.println("ID: " + i.getId() +
                        " | Nome: " + i.getNome() +
                        " | Estoque: " + i.getEstoque());
            }

            System.out.print("Digite o ID do ingrediente: ");
            long id = LeitoresController.lerInteiro(sc);

            Ingrediente ingrediente = repository.findById(id);

            if (ingrediente == null) {
                System.out.println("Ingrediente não encontrado.");
                return;
            }

            System.out.print("Digite a quantidade a adicionar: ");
            int quantidade = LeitoresController.lerInteiro(sc);

            if (quantidade <= 0) {
                System.out.println("Quantidade inválida!");
                return;
            }

            ingrediente.setEstoque(ingrediente.getEstoque() + quantidade);

            repository.atualizar(ingrediente);

            System.out.println("Estoque atualizado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao incrementar estoque: " + e.getMessage());
        }
    }
}
