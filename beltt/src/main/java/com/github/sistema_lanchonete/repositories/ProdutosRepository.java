package com.github.sistema_lanchonete.repositories;

import com.github.sistema_lanchonete.entity.Produtos;
import jakarta.persistence.EntityManager;
import java.util.List;

public class ProdutosRepository {
    private final EntityManager em;

    public ProdutosRepository(EntityManager em) {
        this.em = em;
    }

    // RESOLVE O ERRO: findById(id)
    public Produtos findById(Integer id) {
        // O find precisa do ID no formato correto (Long ou Integer conforme sua Entity)
        return em.find(Produtos.class, Long.valueOf(id));
    }

    // RESOLVE O ERRO: delete(produto)
    public void delete(Produtos produto) {
        try {
            em.getTransaction().begin();
            // Se o objeto estiver "detached" do Hibernate, fazemos o merge antes de remover
            em.remove(em.contains(produto) ? produto : em.merge(produto));
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        }
    }

    // Mantendo os métodos que já corrigimos antes para não quebrar o resto
    public void salvar(Produtos produto) {
        try {
            em.getTransaction().begin();
            em.merge(produto);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        }
    }

    public void create(Produtos produto) { salvar(produto); }
    public void update(Produtos produto) { salvar(produto); }

    public List<Produtos> buscarTodos() {
        return em.createQuery("FROM Produtos", Produtos.class).getResultList();
    }
}