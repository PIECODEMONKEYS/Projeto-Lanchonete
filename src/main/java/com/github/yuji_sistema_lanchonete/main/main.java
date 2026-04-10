package com.github.sistema_lanchonete;

import com.github.sistema_lanchonete.config.CustomizerFactory;
import com.github.sistema_lanchonete.entity.IngredienteEntity;
import com.github.sistema_lanchonete.repositories.IngredienteRepository;
import com.github.sistema_lanchonete.services.EstoqueService;
import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        // 1. Abre a conexão
        EntityManager em = CustomizerFactory.getEntityManager();
        IngredienteRepository repo = new IngredienteRepository(em);

        // 2. Cria e salva um ingrediente novo
        IngredienteEntity novo = new IngredienteEntity();
        novo.setNome("Pão de Hamburguer");
        novo.setQuantidade(50);
        novo.setFrequencia("DIARIO");

        repo.salvar(novo);
        System.out.println("Ingrediente salvo no banco com sucesso!");

        // 3. Testa a busca rápida exigida (HashMap)
        EstoqueService service = new EstoqueService(repo);
        IngredienteEntity busca = service.buscarRapidoPorNome("Pão de Hamburguer");

        if (busca != null) {
            System.out.println("Busca no HashMap funcionou: Temos " + busca.getQuantidade() + " unidades de " + busca.getNome());
        }

        // 4. Encerra o programa
        em.close();
        CustomizerFactory.fechar();
    }
}