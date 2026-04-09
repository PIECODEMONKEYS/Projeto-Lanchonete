package com.github.Gregorys2s.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
//era pra ser entity manager mas o intelij marcou erro dai pedi pro hemini dar outro nome(codgio do prof)
public class CustomizerFactory {

    private static final EntityManagerFactory emf;

    static {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        emf = sessionFactory.unwrap(EntityManagerFactory.class);
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void fechar() {
        emf.close();
    }


}
