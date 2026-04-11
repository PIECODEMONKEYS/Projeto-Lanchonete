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

        // 3. Instanciação dos Controllers com suas dependências
        CaixaController caixa = new CaixaController();
        MenuController menu = new MenuController();
        IngredienteController estoque = new IngredienteController();

        // RESOLVE O ERRO DO CONSTRUTOR: Passando o repositório para o cardápio
        ProdutosController cardapio = new ProdutosController(produtosRepo);

        // Injeção de dependência crucial para o funcionamento do Pedido
        PedidoController pedidos = new PedidoController(caixa);

        try {
            caixa.abrirCaixa();

            boolean rodando = true;
            while (rodando && caixa.isCaixaAberto()) {
                // Exibe apenas o menu visual
                menu.exibirMenuPrincipal();

                // O input é centralizado aqui pelo LeitoresController
                int opcao = LeitoresController.lerInteiro("\nSelecione uma opção: ");

                switch (opcao) {
                    case 1 -> {
                        System.out.println("\n📂 --- ÁREA DO CARDÁPIO ---");
                        cardapio.exibirMenuProdutos();
                    }
                    case 2 -> {
                        System.out.println("\n📦 --- ÁREA DO ESTOQUE ---");
                        estoque.exibirMenuEstoque();
                    }
                    case 3 -> {
                        System.out.println("\n🍔 --- INICIANDO PEDIDO ---");
                        pedidos.novoPedido();
                    }
                    case 4 -> {
                        caixa.fecharCaixa();
                        rodando = false;
                    }
                    default -> System.out.println("⚠️ Opção inválida! Tente novamente.");
                }
            }
        } catch (Exception e) {
            System.err.println("💥 Erro crítico no sistema: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Limpeza de recursos (Hibernate e EntityManager)
            HibernateConfig.shutdown();
            CustomizerFactory.fechar();
            System.out.println("\n✅ Banco de dados desconectado. Aplicação finalizada.");
        }
    }
}