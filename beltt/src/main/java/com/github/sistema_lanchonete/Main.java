package com.github.sistema_lanchonete;


import com.github.sistema_lanchonete.config.CustomizerFactory;
import com.github.sistema_lanchonete.config.FlyWayConfig;
import com.github.sistema_lanchonete.config.HibernateConfig;
import com.github.sistema_lanchonete.repositories.CadastrarIngredientes;
import com.github.sistema_lanchonete.entity.IngredienteEntity;
import com.github.sistema_lanchonete.repositories.IngredienteRepository;
import com.github.sistema_lanchonete.service.EstoqueService;
import jakarta.persistence.EntityManager;

public class Main {
    @SuppressWarnings("GrazieInspectionRunner")
    public static void main(String[] args) {
        EntityManager em = CustomizerFactory.getEntityManager();

        // 1️⃣ Migra o banco
        FlyWayConfig.migrate();

        // 2️⃣ Executa regra de negócio
        CadastrarIngredientes estoque = new CadastrarIngredientes();
        estoque.cadastrar("Presunto", 40);

        // 3️⃣ Encerra recursos
        HibernateConfig.shutdown();

        System.out.println("Aplicação finalizada ✅");


        em.close();
        CustomizerFactory.fechar();
    }

}
