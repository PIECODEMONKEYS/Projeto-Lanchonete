package com.github.sistema_lanchonete;


import com.github.sistema_lanchonete.config.CustomizerFactory;
import com.github.sistema_lanchonete.entity.Produtos;
import com.github.sistema_lanchonete.repositories.ProdutosRepository;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;

public class Main {
    @SuppressWarnings("GrazieInspectionRunner")
    public static void main(String[] args) {
        EntityManager em = CustomizerFactory.getEntityManager();

        // 1️⃣ Migra o banco
        FlyWayConfig.migrate();

        // 2️⃣ Executa regra de negócio
        EstoqueBD estoque = new EstoqueBD();
        estoque.cadastrarIngrediente("Presunto", 40);

        // 3️⃣ Encerra recursos
        HibernateConfig.shutdown();

        System.out.println("Aplicação finalizada ✅");


        em.close();
        CustomizerFactory.fechar();
    }

}
