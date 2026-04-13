package com.github.sistema_lanchonete.controller;

import com.github.sistema_lanchonete.exceptions.AberturaCaixaNegocioException;
import com.github.sistema_lanchonete.exceptions.LeituraUsuarioNegocioException;

import java.util.Scanner;

public class CaixaController {
    private double saldoEmCaixa;
    private boolean caixaAberto = false;
    private int totalPedidosNoTurno = 0; // Para o resumo final
    private double totalVendidoNoTurno = 0; // Para o resumo final

    public void abrirCaixa(Scanner sc) {
        System.out.println("=== ABERTURA DE CAIXA ===");
        try {
            System.out.println("Informe o valor inicial (fundo de caixa): ");
            double valorInicial = LeitoresController.lerDouble(sc);

            if (valorInicial < 0) {
                throw new AberturaCaixaNegocioException("Não é possível abrir o caixa com valor negativo.");
            }

            this.saldoEmCaixa = valorInicial;
            this.caixaAberto = true;
            System.out.println("Caixa aberto com sucesso! Saldo: R$ " + this.saldoEmCaixa);

        } catch (AberturaCaixaNegocioException e) {
            System.err.println(e.getMessage());
            abrirCaixa(sc); // Tenta novamente em caso de valor negativo
        } catch (Exception e) {
            System.err.println("Erro ao processar o valor de abertura. Tente novamente.");
            abrirCaixa(sc); // Reinicia o processo em caso de erro de digitação
        }
    }

    // MÉTODO QUE FALTAVA PARA A MAIN FUNCIONAR:
    public void fecharCaixa() {
        System.out.println("\n==============================");
        System.out.println("      RESUMO DO TURNO         ");
        System.out.println("==============================");
        System.out.println("Pedidos realizados: " + totalPedidosNoTurno);
        System.out.println("Total vendido: R$ " + totalVendidoNoTurno);
        System.out.println("Saldo final em gaveta: R$ " + saldoEmCaixa);
        System.out.println("==============================");
        this.caixaAberto = false;
    }

    // Método para ser usado pelo futuro PedidoController
    public void registrarVenda(double valor) {
        this.totalVendidoNoTurno += valor;
        this.saldoEmCaixa += valor;
        this.totalPedidosNoTurno++;
    }

    public boolean isCaixaAberto() {
        return caixaAberto;
    }
}