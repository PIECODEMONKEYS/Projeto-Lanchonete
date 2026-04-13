package com.github.sistema_lanchonete.repositories;

import com.github.sistema_lanchonete.entity.Ingrediente;
import jakarta.persistence.EntityManager;
import java.util.List;

public class IngredienteRepository {

    private final EntityManager entityManager;

    // Construtor que recebe o EntityManager
    public IngredienteRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // MÉTODO QUE RESOLVE O ERRO: findAll
    public List<Ingrediente> findAll() {
        return entityManager.createQuery("from IngredienteEntity", Ingrediente.class).getResultList();
    }
    public List<Ingrediente> buscarTodos() {
        return findAll();
    }

    // MÉTODO NOVO: Adicionado para resolver o erro 'cannot find symbol method salvar'
    public void salvar(Ingrediente ingrediente) {
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
    public void create(Ingrediente ingrediente) {
        salvar(ingrediente);
    }
}