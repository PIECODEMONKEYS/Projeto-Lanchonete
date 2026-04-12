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

    // MÉTODO NOVO: Adicionado para resolver o erro 'cannot find symbol method salvar'
    public void salvar(IngredienteEntity ingrediente) {
        try {
            entityManager.getTransaction().begin();
            // O merge é mais seguro pois lida com objetos novos e atualizações
            entityManager.merge(ingrediente);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }

    // Mantém compatibilidade com quem usa o método create
    public void create(IngredienteEntity ingrediente) {
        salvar(ingrediente);
    }
}