package com.github.sistema_lanchonete;

import com.github.sistema_lanchonete.config.CustomizerFactory;
import com.github.sistema_lanchonete.config.FlyWayConfig;
import com.github.sistema_lanchonete.config.HibernateConfig;
import com.github.sistema_lanchonete.controller.CaixaController;
import com.github.sistema_lanchonete.controller.MenuController;
import com.github.sistema_lanchonete.controller.LeitoresController;
import com.github.sistema_lanchonete.controller.IngredienteController; // IMPORT CORRIGIDO
import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        FlyWayConfig.migrate();

        CaixaController caixa = new CaixaController();
        MenuController menu = new MenuController();

        try {
            caixa.abrirCaixa();
        } catch (Exception e) {
            System.err.println("Erro fatal: " + e.getMessage());
            return;
        }

        boolean rodando = true;
        while (rodando && caixa.isCaixaAberto()) {
            menu.exibirMenuPrincipal();
            int opcao = LeitoresController.lerInteiro("Sua opção: ");

            switch (opcao) {
                case 1:
                    System.out.println("-> Cardápio...");
                    break;
                case 2:
                    System.out.println("-> Estoque...");
                    // Agora a Main vai reconhecer esta classe:
                    IngredienteController ingredienteController = new IngredienteController();
                    ingredienteController.exibirMenuEstoque();
                    break;
                case 3:
                    System.out.println("-> Pedido...");
                    break;
                case 4:
                    caixa.fecharCaixa();
                    rodando = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

        HibernateConfig.shutdown();
        CustomizerFactory.fechar();
        System.out.println("Aplicação finalizada ✅");
    }
}