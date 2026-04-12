package com.github.sistema_lanchonete.controller;

import java.util.Scanner;

public class LeitoresController {

    public static int lerInteiro(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    public static double lerDouble(S) {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Digite algum preço ou valor válido.");
            }
        }
    }

    public static String lerString(String mensagem) {
        System.out.print(mensagem);
        String entrada = sc.nextLine();
        return (entrada != null) ? entrada.trim() : "";
    }
}