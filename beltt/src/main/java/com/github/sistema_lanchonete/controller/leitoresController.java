package com.github.sistema_lanchonete.controller;

import com.github.sistema_lanchonete.exceptions.DataNegocioException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class leitoresController {
    private Scanner sc;

    public static Integer leitorInteger(Scanner sc)
    {
        while(true){
            try {
                return Integer.parseInt(sc.nextLine());
            }catch (NumberFormatException e){
                System.out.println("digite um número inteiro");
            }
        }
    }
    public static double lerDouble(Scanner sc){
        while (true){
            try {
                return Double.parseDouble(sc.nextLine());
            }catch (InputMismatchException e){
                System.out.println("Digite algum preço ");
            }
        }
    }
    public static String lerString(Scanner sc)
    {
        String entrada = sc.nextLine();
        return (entrada != null) ? entrada.trim().toLowerCase() : "";
    }

    public static Integer lerDia(Scanner sc)
    {
        while(true){
            try {
                Integer dia = Integer.valueOf(sc.nextLine());
                if(dia <= 31 && dia > 0) {
                    return dia;
                } else {
                    throw new DataNegocioException("Dia inserido invalido");
                }
            }catch (DataNegocioException e){
                System.out.println("Erro inesperado identificado: " + e.getMessage());
            } catch (InputMismatchException e)
            {
                System.out.println("Digite um dia inteiro ex: 01");
            }
        }
    }

    public static Integer lerMes(Scanner sc)
    {
        while(true){
            try {
                Integer mes = Integer.valueOf(sc.nextLine());
                if(mes <= 12 && mes > 0) {
                    return mes;
                } else {
                    throw new DataNegocioException("Mes inserido invalido");
                }
            }catch (DataNegocioException e){
                System.out.println("Erro inesperado identificado: " + e.getMessage());
            } catch (InputMismatchException e)
            {
                System.out.println("Digite um mes inteiro ex: 05");
            }
        }
    }

    public static Integer lerAno(Scanner sc)
    {
        while(true){
            try {
                Integer ano = Integer.valueOf(sc.nextLine());
                if(ano > 0 && ano >= 2026) {
                    return ano;
                } else {
                    throw new DataNegocioException("Ano inserido invalido");
                }
            }catch (DataNegocioException e){
                System.out.println("Erro inesperado identificado: " + e.getMessage());
            } catch (InputMismatchException e)
            {
                System.out.println("Digite um ano inteiro ex: 2026");
            }
        }
    }
}
