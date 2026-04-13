package com.github.sistema_lanchonete.repositories;

import com.github.sistema_lanchonete.entity.Produtos;
import com.github.sistema_lanchonete.exceptions.PersistenciaProdutoRepositoryException;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProdutosRepository {
    private EntityManager em;

    public ProdutosRepository(EntityManager em) {
        this.em = em;
    }

    public Produtos findById(long id) {
        return em.find(Produtos.class, id);
    }

    public void salvar(Produtos produtos) {
        try {
            em.getTransaction().begin();
            em.persist(produtos);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            //noinspection GrazieInspectionRunner
            throw new PersistenciaProdutoRepositoryException("Erro ao tentar salvar o item " + produtos.getNome(), e);
        }
    }


    public void atualizar(Produtos produtos) {
        try {
            em.getTransaction().begin();
            em.merge(produtos);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            //noinspection GrazieInspectionRunner
            throw new PersistenciaProdutoRepositoryException("Erro ao tentar atualizar o item " + produtos.getNome(), e);
        }
    }

    public void delete(Produtos produtos) {
        try{
            em.getTransaction().begin();
            em.remove(em.contains(produtos) ? produtos : em.merge(produtos));
            em.getTransaction().commit();
        }catch(Exception e){
            if(em.getTransaction().isActive())
            {
                em.getTransaction().rollback();
            }
            //noinspection GrazieInspectionRunner
            throw new PersistenciaProdutoRepositoryException("Erro ao tentar deletar o item " + produtos.getNome(), e);
        }
    }
    public List<Produtos> buscarTodos() {
        return em.createQuery("select c from Produtos c", Produtos.class).getResultList();
    }

    public List<Produtos> findByName(String name) {
        return em.createQuery("select c from Produtos c where c.nome = :name",
                        Produtos.class)
                .setParameter("name", name)
                .getResultList();
    }
}