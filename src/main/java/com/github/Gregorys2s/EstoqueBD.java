package com.github.Gregorys2s;

import com.github.Gregorys2s.config.HibernateConfig;
import com.github.Gregorys2s.entity.IngredienteEntity;
import org.hibernate.Session;


public class EstoqueBD {

    public void cadastrarIngrediente(String nome, int estoque) {

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
