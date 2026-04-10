package com.github.sistema_lanchonete.repositories;

import com.github.sistema_lanchonete.entity.Produtos;
import com.github.sistema_lanchonete.exceptions.PersistenciaRepositoryException;
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

    public void create(Produtos produtos) {
        try {
            em.getTransaction().begin();
            em.persist(produtos);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            //noinspection GrazieInspectionRunner
            throw new PersistenciaRepositoryException("Erro ao tentar salvar o item " + produtos.getNome(), e);
        }
    }


    public void update(Produtos produtos) {
        em.getTransaction().begin();
        em.persist(produtos);
        em.getTransaction().commit();
    }

    public void delete(Produtos produtos) {
        em.getTransaction().begin();
        em.remove(em.contains(produtos) ? produtos : em.merge(produtos));
        em.getTransaction().commit();
    }
    public List<Produtos> findAll() {
        return em.createQuery("select c from cardapio c", Produtos.class).getResultList();
    }

    public List<Produtos> findByName(String name) {
        return em.createQuery("select c from produtos c where c.nome = :name",
                        Produtos.class)
                .setParameter("name", name)
                .getResultList();
    }
}
