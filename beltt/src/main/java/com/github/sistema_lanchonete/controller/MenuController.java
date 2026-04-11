package com.github.sistema_lanchonete.controller;

public class MenuController {
    public void exibirMenuPrincipal() {
        System.out.println("\n--- SISTEMA LANCHONETE ---");
        System.out.println("1. Cardápio (Produtos)");
        System.out.println("2. Estoque (Ingredientes)");
        System.out.println("3. Realizar Pedido");
        System.out.println("4. Fechar Caixa e Sair");
        System.out.print("Sua opção: ");
    }
}