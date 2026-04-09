package com.github.sistema_lanchonete.controller;

import java.util.Scanner;

public class leitoresController {
    private Scanner sc;

    public Integer leitorInteger(Scanner sc)
    {
        while(true){
            try {
                return Integer.parseInt(sc.nextLine());
            }catch (NumberFormatException e){
                System.out.println("digite um número inteiro");
            }
        }
    }
    public double lerDouble(Scanner sc){
        while (true){
            try {
                return Double.parseDouble(sc.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Digite algum preço ");
            }
        }
    }
    public static String lerString(Scanner sc)
    {
        String entrada = sc.nextLine();
        return (entrada != null) ? entrada.trim().toLowerCase() : "";
    }
}
