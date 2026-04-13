package com.github.sistema_lanchonete.repositories;

import jakarta.persistence.EntityManager;
import com.github.sistema_lanchonete.entity.Pagamento;

import java.util.List;

public class PagamentoRepository {
    private final EntityManager em;

    public PagamentoRepository(EntityManager em){
        this.em = em;
    }

    public void salvar(Pagamento pagamento){
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
    public Pagamento buscarPorId(Long id){
        return em.find(Pagamento.class, id);
    }

    public List<Pagamento> listarTodos(){
        return em.createQuery("SELECT p FROM PagamentoEntity p", Pagamento.class)
                .getResultList();
    }

    public void atualizar(Pagamento pagamento){
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

            Pagamento pagamento = em.find(Pagamento.class, id);

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
