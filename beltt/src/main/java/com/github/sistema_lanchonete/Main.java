package com.github.sistema_lanchonete;

import com.github.sistema_lanchonete.config.CustomizerFactory;
import com.github.sistema_lanchonete.config.FlyWayConfig;
import com.github.sistema_lanchonete.config.HibernateConfig;
import com.github.sistema_lanchonete.controller.*;
import com.github.sistema_lanchonete.repositories.ProdutosRepository;
import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        // 1. Inicialização do Banco de Dados
        FlyWayConfig.migrate();

        // 2. Preparação do EntityManager e Repositórios necessários para os Controllers
        EntityManager em = CustomizerFactory.getEntityManager();
        ProdutosRepository produtosRepo = new ProdutosRepository(em);

        // 3. Instanciação dos Controllers com a suas dependências
        CaixaController caixa = new CaixaController();
        MenuController menu = new MenuController();
        IngredienteController estoque = new IngredienteController();

        ProdutosController cardapio = new ProdutosController(produtosRepo);

        PedidoController pedidos = new PedidoController(caixa);

        try {
            caixa.abrirCaixa();

            boolean rodando = true;
            while (rodando && caixa.isCaixaAberto()) {
                menu.exibirMenuPrincipal();

                int opcao = LeitoresController.lerInteiro("\nSelecione uma opção: ");

                switch (opcao) {
                    case 1 -> {
                        System.out.println("\n--- ÁREA DO CARDÁPIO ---");
                        cardapio.exibirMenuProdutos();
                    }
                    case 2 -> {
                        System.out.println("\n--- ÁREA DO ESTOQUE ---");
                        estoque.exibirMenuEstoque();
                    }
                    case 3 -> {
                        System.out.println("\n--- INICIANDO PEDIDO ---");
                        pedidos.novoPedido();
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
            HibernateConfig.shutdown();
            CustomizerFactory.fechar();
            System.out.println("\nBanco de dados desconectado. Aplicação finalizada.");
        }
    }
}