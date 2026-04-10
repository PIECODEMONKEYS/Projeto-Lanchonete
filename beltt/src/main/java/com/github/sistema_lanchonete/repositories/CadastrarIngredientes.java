package com.github.sistema_lanchonete.repositories;

//essa classe sinceramente nao tenho ideia de onde por, por isso coloque em repository

import com.github.sistema_lanchonete.config.HibernateConfig;
import com.github.sistema_lanchonete.entity.IngredienteEntity;
import org.hibernate.Session;


public class CadastrarIngredientes {

    public void cadastrar(String nome, int estoque) {

        // 1️⃣ Abre sessão com o banco
        Session session = HibernateConfig.getSessionFactory().openSession();

        // 2️⃣ Inicia transação
        session.beginTransaction();

        // 3️⃣ Cria a entidade
        IngredienteEntity ingrediente =
                new IngredienteEntity(nome, estoque);

        // 4️⃣ Salva no banco
        session.persist(ingrediente);

        // 5️⃣ Confirma a transação
        session.getTransaction().commit();

        // 6️⃣ Fecha a sessão
        session.close();
    }
    }
