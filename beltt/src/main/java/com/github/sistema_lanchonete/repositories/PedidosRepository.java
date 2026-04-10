package com.github.sistema_lanchonete.repositories;

import com.github.sistema_lanchonete.entity.Pedidos;
import com.github.sistema_lanchonete.exceptions.PersistencePedidosException;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;

public class PedidosRepository {
    private EntityManager em;

    public PedidosRepository(EntityManager em) {
        this.em = em;
    }

    public Pedidos findById(long id)
    {
        return em.find(Pedidos.class, id);
    }

    public void create(Pedidos pedidos)
    {
        try{
            em.getTransaction().begin();
            em.persist(pedidos);
            em.getTransaction().commit();
        } catch (Exception e)
        {
            if(em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw new PersistencePedidosException("Erro na geração de produtos: " + pedidos.getId(), e)
        }
    }

    public List<Pedidos> findAll()
    {
        return em.createQuery("select p from pedidos p", Pedidos.class).getResultList();
    }

    public findByDay(LocalDateTime dia)
}
