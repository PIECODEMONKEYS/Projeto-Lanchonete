package com.github.sistema_lanchonete.repositories;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import com.github.sistema_lanchonete.entity.PagamentoEntity;

import java.util.List;

public class PagamentoRepository {
    private final EntityManager em;

    public PagamentoRepository(EntityManager em){
        this.em = em;
    }

    public void salvar(PagamentoEntity pagamento){
        try{
            em.getTransaction().begin();
            em.persist(pagamento);
            em.getTransaction().commit();
        } catch (Exception e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao salvar pagamento", e);
        }
    }
    public PagamentoEntity buscarPorId(Long id){
        return em.find(PagamentoEntity.class, id);
    }

    public List<PagamentoEntity> listarTodos(){
        return em.createQuery("SELECT p FROM PagamentoEntity p", PagamentoEntity.class)
                .getResultList();
    }

    public void atualizar(PagamentoEntity pagamento){
        try{
            em.getTransaction().begin();
            em.merge(pagamento);
            em.getTransaction().commit();
        }catch (Exception e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao atualizar pagamento", e);
        }
    }
    public void deletar(Long id){
        try{
            em.getTransaction().begin();

            PagamentoEntity pagamento = em.find(PagamentoEntity.class, id);

            if(pagamento != null){
                em.remove(pagamento);
            }

            em.getTransaction().commit();
        }catch (Exception e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw new RuntimeException("erro ao deletar pagamento", e);
        }
    }
}
