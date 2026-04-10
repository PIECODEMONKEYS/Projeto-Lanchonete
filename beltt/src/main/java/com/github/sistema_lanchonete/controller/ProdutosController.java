package com.github.sistema_lanchonete.controller;

import com.github.sistema_lanchonete.entity.Produtos;
import com.github.sistema_lanchonete.exceptions.PersistenciaRepositoryException;
import com.github.sistema_lanchonete.exceptions.RegraNegocioException;
import com.github.sistema_lanchonete.repositories.ProdutosRepository;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
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
            String nome = leitoresController.lerString(sc);
            if(nome == null || nome.isBlank())
            {
                throw new RegraNegocioException("Nome nao pode ser vazio");
            }
            produtos.setNome(nome);
            //noinspection GrazieInspectionRunner
            System.out.println("Digite o valor do produto");
            double valor = leitoresController.lerDouble(sc);
            if(valor <= 0)
            {
                throw new RegraNegocioException("Valor não pode ser zero nem negativo");
            }
            produtos.setPreco(BigDecimal.valueOf(valor));

            this.repository.create(produtos);
            System.out.println("Produto criado com sucesso");
        } catch (RegraNegocioException e) {
            throw new RegraNegocioException("Erro no envio de valores");
        } catch (PersistenciaRepositoryException e) {
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
            String nome = leitoresController.lerString(sc);
            if(nome == null || nome.isBlank())
            {
                throw new RegraNegocioException("Nome nao pode ser vazio");
            }
            produtos.setNome(nome);
            //noinspection GrazieInspectionRunner
            System.out.println("Digite o valor do produto");
            double valor = leitoresController.lerDouble(sc);
            if(valor <= 0)
            {
                throw new RegraNegocioException("Valor não pode ser zero nem negativo");
            }
            produtos.setPreco(BigDecimal.valueOf(valor));

            this.repository.update(produtos);
            System.out.println("Produto criado com sucesso");
        } catch (RegraNegocioException e) {
            throw new RegraNegocioException("Erro no envio de valores");
        } catch (PersistenciaRepositoryException e) {
            System.out.println("Erro ao enviar dados: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado no sistema" + e);
        }
    }

    public void deletarItem(Scanner sc)
    {
        try{
            System.out.println("Digite o id do produto que deseja remover: ");
            Integer id = leitoresController.leitorInteger(sc);

            Produtos produto = this.repository.findById(id);
            if(produto == null)
            {
                throw new RegraNegocioException("operacao cancelada id invalido");
            }

            System.out.println("Deseja realmente deletar o item: digite SIM para confirmar");
            String confirmacao = leitoresController.lerString(sc);
            if(confirmacao.equals("SIM"))//equals normal pra o usuario ter ctz que quer deletar o item
            {
                this.repository.delete(produto);
                System.out.println("Item deletado com sucesso");
            } else {
                System.out.println("Operação cancelada");
            }
        } catch(RegraNegocioException e)
        {
            System.out.println("Aviso: " + e.getMessage());
        } catch (PersistenciaRepositoryException e)
        {
            System.out.println("Erro critico na conexao com o banco de dados: " + e.getMessage());
        } catch (RuntimeException e)
        {
            System.out.println("Erro inesperado ao remover item: ", e);
        }
    }
}
