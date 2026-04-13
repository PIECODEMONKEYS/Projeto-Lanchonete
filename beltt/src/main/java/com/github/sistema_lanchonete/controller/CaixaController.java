package com.github.sistema_lanchonete.controller;

import com.github.sistema_lanchonete.exceptions.AberturaCaixaNegocioException;
import java.util.Scanner;

public class CaixaController {
    private double saldoEmCaixa;
    private boolean caixaAberto = false;
    private int totalPedidosNoTurno = 0; // Para o resumo final
    private double totalVendidoNoTurno = 0; // Para o resumo final
    Scanner sc = new Scanner(System.in);

    public void abrirCaixa() {
        System.out.println("=== ABERTURA DE CAIXA ===");

        while (true) {
            try {
                System.out.print("Informe o valor inicial: ");
                double valorInicial = LeitoresController.lerDouble(sc);

                if (valorInicial < 0) {
                    throw new AberturaCaixaNegocioException("Valor negativo");
                }

                this.saldoEmCaixa = valorInicial;
                this.caixaAberto = true;

                System.out.println("Saldo: R$ " + String.format("%.2f", saldoEmCaixa));
                break;

            }catch (AberturaCaixaNegocioException e) {
                    System.out.println(e.getMessage());
            }
        }
    }

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