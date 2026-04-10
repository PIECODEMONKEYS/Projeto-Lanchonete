package com.github.sistema_lanchonete.repositories;

import com.github.sistema_lanchonete.entity.IngredienteEntity;
import jakarta.persistence.EntityManager;
import java.util.List;

public class IngredienteRepository {
    private EntityManager em;

    public IngredienteRepository(EntityManager em) {
        this.em = em;
    }

    public void salvar(IngredienteEntity ingrediente) {
        em.getTransaction().begin();
        em.persist(ingrediente);
        em.getTransaction().commit();
    }

    public List<IngredienteEntity> buscarTodos() {
        return em.createQuery("FROM IngredienteEntity", IngredienteEntity.class).getResultList();
    }
}