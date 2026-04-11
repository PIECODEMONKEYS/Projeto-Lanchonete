package com.github.sistema_lanchonete.repositories;

import com.github.sistema_lanchonete.entity.IngredienteEntity;
import jakarta.persistence.EntityManager;
import java.util.List;



public class IngredienteRepository {

    private final EntityManager entityManager;

    // Construtor que recebe o EntityManager
    public IngredienteRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // MÉTODO QUE RESOLVE O ERRO: findAll
    public List<IngredienteEntity> findAll() {
        return entityManager.createQuery("from IngredienteEntity", IngredienteEntity.class).getResultList();
    }
    public List<IngredienteEntity> buscarTodos() {
        return findAll();
    }

    // MÉTODO QUE RESOLVE O ERRO: create
    public void create(IngredienteEntity ingrediente) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(ingrediente);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }

    public List<IngredienteEntity> buscarTodos() {
        return em.createQuery("FROM IngredienteEntity", IngredienteEntity.class).getResultList();
    }

    public List<IngredienteEntity> findByName(String name) {
        return em.createQuery("select i from ingredientes i where i.nome = :name",
                        IngredienteEntity.class)
                .setParameter("name", name)
                .getResultList();
    }

    public IngredienteEntity findById(long id) {
        return em.find(IngredienteEntity.class, id);
    }

}