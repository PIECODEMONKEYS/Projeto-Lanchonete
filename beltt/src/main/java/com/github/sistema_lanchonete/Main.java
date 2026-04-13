package com.github.sistema_lanchonete;

import com.github.sistema_lanchonete.config.CustomizerFactory;
import com.github.sistema_lanchonete.config.FlyWayConfig;
import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {

        try {
            // 🔥 1. PRIMEIRO: cria tabelas
            FlyWayConfig.migrate();

            // 🔥 2. DEPOIS: abre Hibernate
            EntityManager em = CustomizerFactory.getEntityManager();

            System.out.println(" Banco conectado com sucesso!");

            em.createNativeQuery("SELECT 1").getSingleResult();

            System.out.println(" Query executada com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            e.printStackTrace();
        }
    }
}