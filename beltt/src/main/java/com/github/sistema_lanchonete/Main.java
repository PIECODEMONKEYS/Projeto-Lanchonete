package com.github.sistema_lanchonete;

import com.github.sistema_lanchonete.config.CustomizerFactory;
import com.github.sistema_lanchonete.config.FlyWayConfig;
import com.github.sistema_lanchonete.config.HibernateConfig;
import com.github.sistema_lanchonete.controller.*;
import com.github.sistema_lanchonete.repositories.ProdutosRepository;
import jakarta.persistence.EntityManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1. Inicialização do Banco de Dados
        FlyWayConfig.migrate();
//nao kkkkk
        // 2. Preparação do EntityManager e Repositórios necessários para os Controllers
        EntityManager em = CustomizerFactory.getEntityManager();
        ProdutosRepository produtosRepo = new ProdutosRepository(em);

        // 3. Instanciação dos Controllers com a suas dependências
        CaixaController caixa = new CaixaController();
        MenuController menu = new MenuController();
        IngredienteController estoque = new IngredienteController();
        Scanner sc = new Scanner(System.in);
        // RESOLVE O ERRO DO CONSTRUTOR: Passando o repositório para o cardápio
        ProdutosController cardapio = new ProdutosController(produtosRepo);

        // Arrumar isso com urgencia
        //PedidoController pedidos = new PedidoController(caixa);

        try {
            caixa.abrirCaixa(sc);

            boolean rodando = true;
            while (rodando && caixa.isCaixaAberto()) {
                // Exibe apenas o menu visual
                menu.exibirMenuPrincipal();

                // O input é centralizado aqui pelo LeitoresController
                System.out.println("\nSelecione uma opção: ");
                int opcao = LeitoresController.lerInteiro(sc);

                switch (opcao) {
                    case 1 -> {
                        System.out.println("\n\tÁREA DO CARDÁPIO");
                        cardapio.exibirMenuProdutos();
                    }
                    case 2 -> {
                        System.out.println("\n\tÁREA DO ESTOQUE");
                        estoque.exibirMenuEstoque(sc);
                    }
                    case 3 -> {
                        System.out.println("\n\tINICIANDO PEDIDO");
                        //ARRUMAR ESTA BOSTA
                        //pedidos.novoPedido();
                    }
                    case 4 -> {
                        caixa.fecharCaixa();
                        rodando = false;
                    }
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            }
        } catch (Exception e) {
            System.err.println("Erro crítico no sistema: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Limpeza de recursos (Hibernate e EntityManager)
            HibernateConfig.shutdown();
            CustomizerFactory.fechar();
            sc.close();
            System.out.println("\nBanco de dados desconectado. Aplicação finalizada.");
        }
    }
}