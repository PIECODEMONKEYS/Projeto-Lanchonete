package com.github.sistema_lanchonete.repositories;

import com.github.sistema_lanchonete.entity.PedidoEntity;
import jakarta.persistence.EntityManager;

public class PedidoRepository {
    private final EntityManager em;

    public PedidoRepository(EntityManager em) { this.em = em; }

    public void salvar(PedidoEntity pedido) {
        try {
            em.getTransaction().begin();
            em.persist(pedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        }
    }
}