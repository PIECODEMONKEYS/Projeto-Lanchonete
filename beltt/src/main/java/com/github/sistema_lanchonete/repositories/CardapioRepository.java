package com.github.sistema_lanchonete.repositories;

import com.github.sistema_lanchonete.entity.Cardapio;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CardapioRepository {
    private EntityManager em;

    public CardapioRepository(EntityManager em) {
        this.em = em;
    }

    public Cardapio findById(int id) {
        return em.find(Cardapio.class, id);
    }
    public void create(Cardapio cardapio) {
        em.getTransaction().begin();
        em.persist(cardapio);
        em.getTransaction().commit();
    }


    public void update(Cardapio cardapio) {
        em.getTransaction().begin();
        em.persist(cardapio);
        em.getTransaction().commit();
    }

    public void delete(Cardapio cardapio) {
        em.getTransaction().begin();
        em.remove(em.contains(cardapio) ? cardapio : em.merge(cardapio));
        em.getTransaction().commit();
    }
    public List<Cardapio> findAll() {
        return em.createQuery("select c from cardapio c", Cardapio.class).getResultList();
    }

    public List<Cardapio> findByName(String name) {
        return em.createQuery("select c from produtos c where c.nome = :name",
                        Cardapio.class)
                .setParameter("name", name)
                .getResultList();
    }
}
