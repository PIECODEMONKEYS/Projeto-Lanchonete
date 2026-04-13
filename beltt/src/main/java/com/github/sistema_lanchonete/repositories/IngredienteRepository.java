package com.github.sistema_lanchonete.repositories;

import com.github.sistema_lanchonete.entity.Ingrediente;
import com.github.sistema_lanchonete.exceptions.AcharIngredienteException;
import com.github.sistema_lanchonete.exceptions.PersistenciaIngredienteRepositoryException;
import jakarta.persistence.EntityManager;
import java.util.List;

public class IngredienteRepository {
    private EntityManager em;

    public IngredienteRepository(EntityManager em) {
        this.em = em;
    }

    public void salvar(Ingrediente ingrediente) {
        try {
            em.getTransaction().begin();
            em.persist(ingrediente);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            //noinspection GrazieInspectionRunner
            throw new PersistenciaIngredienteRepositoryException("Erro ao tentar salvar o item " + ingrediente.getNome(), e);
        }
    }


    public void atualizar(Ingrediente ingrediente) {
        try {
            em.getTransaction().begin();
            em.merge(ingrediente);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            //noinspection GrazieInspectionRunner
            throw new PersistenciaIngredienteRepositoryException("Erro ao tentar atualizar o item " + ingrediente.getNome(), e);
        }
    }


    public void remover(Ingrediente ingrediente) {
        try{
            em.getTransaction().begin();
            em.remove(em.contains(ingrediente) ? ingrediente : em.merge(ingrediente));
            em.getTransaction().commit();
        }catch(Exception e){
            if(em.getTransaction().isActive())
            {
                em.getTransaction().rollback();
            }
            //noinspection GrazieInspectionRunner
            throw new PersistenciaIngredienteRepositoryException("Erro ao tentar deletar o item " + ingrediente.getNome(), e);
        }
    }

    public List<Ingrediente> buscarTodos() {
        return em.createQuery("FROM Ingrediente", Ingrediente.class).getResultList();
    }

    public Ingrediente acharPeloNome(String name) {
        try{
            return em.createQuery("select i from Ingrediente i where i.nome = :name",
                            Ingrediente.class)
                    .setParameter("name", name)
                    .getResultList()
                    .stream()
                    .findFirst()
                    .orElse(null);
        } catch(Exception e){
            throw new AcharIngredienteException("Erro ao tentar achar pelo nome " + name, e);
        }
    }

    public Ingrediente findById(long id) {
        return em.find(Ingrediente.class, id);
    }

    public boolean existePorNome(String nome) {
        Long count = em.createQuery("SELECT COUNT(i) FROM Ingrediente i WHERE LOWER(i.nome) = LOWER(:nome)", Long.class)
                .setParameter("nome", nome.trim())
                .getSingleResult();
        return count > 0;
    }
}