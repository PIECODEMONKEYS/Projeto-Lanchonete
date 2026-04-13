package com.github.sistema_lanchonete.controller;



import com.github.sistema_lanchonete.entity.Produtos;
import com.github.sistema_lanchonete.exceptions.CardapioVazioException;
import com.github.sistema_lanchonete.exceptions.PersistenciaProdutoRepositoryException;
import com.github.sistema_lanchonete.exceptions.RegraNegocioException;
import com.github.sistema_lanchonete.repositories.ProdutosRepository;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ProdutosController {
    private final ProdutosRepository repository;
    public ProdutosController(ProdutosRepository repository) {
        this.repository = repository;
    }
    //RegraNegocioException e PersistenciaRepositoryException explicada na pasta exceptions
    public void adicionarItem(Scanner sc, EntityManager em, Produtos produtos)
    {
        try{
            //noinspection GrazieInspectionRunner
            System.out.println("Digite o nome do produto");
            String nome = LeitoresController.lerString(sc);
            if(nome == null || nome.isBlank())
            {
                throw new RegraNegocioException("Nome nao pode ser vazio");
            }
            produtos.setNome(nome);
            //noinspection GrazieInspectionRunner
            System.out.println("Digite o valor do produto");
            double valor = LeitoresController.lerDouble(sc);
            if(valor <= 0)
            {
                throw new RegraNegocioException("Valor não pode ser zero nem negativo");
            }
            produtos.setPreco(BigDecimal.valueOf(valor));

            this.repository.salvar(produtos);
            System.out.println("Produto criado com sucesso");
        } catch (RegraNegocioException e) {
            throw new RegraNegocioException("Erro no envio de valores");
        } catch (PersistenciaProdutoRepositoryException e) {
            System.out.println("Erro ao enviar dados: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado no sistema" + e);
        }
    }

    public void atualizarItem(Scanner sc, EntityManager em, Produtos produtos)
    {
        try{
            //noinspection GrazieInspectionRunner
            System.out.println("Digite o nome do produto");
            String nome = LeitoresController.lerString(sc);
            if(nome == null || nome.isBlank())
            {
                throw new RegraNegocioException("Nome nao pode ser vazio");
            }
            produtos.setNome(nome);
            //noinspection GrazieInspectionRunner
            System.out.println("Digite o valor do produto");
            double valor = LeitoresController.lerDouble(sc);
            if(valor <= 0)
            {
                throw new RegraNegocioException("Valor não pode ser zero nem negativo");
            }
            produtos.setPreco(BigDecimal.valueOf(valor));

            this.repository.atualizar(produtos);
            System.out.println("Produto criado com sucesso");
        } catch (RegraNegocioException e) {
            throw new RegraNegocioException("Erro no envio de valores");
        } catch (PersistenciaProdutoRepositoryException e) {
            System.out.println("Erro ao enviar dados: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado no sistema" + e);
        }
    }

    public void deletarItem(Scanner sc)
    {
        try{
            System.out.println("Digite o id do produto que deseja remover: ");
            Integer id = LeitoresController.lerInteiro(sc);

            Produtos produto = this.repository.findById(id);
            if(produto == null)
            {
                throw new RegraNegocioException("operacao cancelada id invalido");
            }

            System.out.println("Deseja realmente deletar o item: digite SIM para confirmar");
            String confirmacao = LeitoresController.lerString(sc);
            if(confirmacao.equals("sim"))//equals normal pra o usuario ter ctz que quer deletar o item
            {
                this.repository.delete(produto);
                System.out.println("Item deletado com sucesso");
            } else {
                System.out.println("Operação cancelada");
            }
        } catch(RegraNegocioException e)
        {
            System.out.println("Aviso: " + e.getMessage());
        } catch (PersistenciaProdutoRepositoryException e)
        {
            System.out.println("Erro critico na conexao com o banco de dados: " + e.getMessage());
        } catch (RuntimeException e)
        {
            System.out.println("Erro inesperado ao remover item: " + e);
        }
    }

    public void cardapio(Scanner sc)
    {
        System.out.println("1. Listar produtos" +
                "\n2. Procurar por nome" +
                "\n3. Voltar ao menu");
        boolean ativo = true;
        do{
            int opc = LeitoresController.lerInteiro(sc);
            switch (opc) {
                case 1: listarTodos(); break;
                case 2:
                    try{
                        System.out.println("Digite o nome exato do produto");
                        String nome = LeitoresController.lerString(sc);
                        Produtos achado = repository.acharPeloNome(nome);
                        System.out.println("ID: " + achado.getId());
                        System.out.println("Nome: " + achado.getNome());
                        System.out.println("Preço: R$ " + achado.getPreco());
                        ativo = false;
                    } catch (Exception e) {
                        System.out.println("Erro ao deletar ingrediente: " + e.getMessage());
                    }
                    break;
                case 3: ativo = false; break;
                default:
                    System.out.println("Selecione uma opcao do menu");
                    break;
            }
        }while(ativo);
    }

    public void listarTodos()
    {
        System.out.println("\n\tCONSULTANDO BANCO DE DADOS");
        try {
            List<Produtos> lista = repository.buscarTodos();
            if (lista.isEmpty()) {
                throw new CardapioVazioException("Cardapio esta vazio");
            } else {
                System.out.printf("%-5s | %-20s | %-10s%n", "ID", "NOME", "PRECO");
                for (Produtos p : lista) {
                    System.out.printf("%-5d | %-20s | %-10.2f%n", p.getId(), p.getNome(), p.getPreco());
                }
            }
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}