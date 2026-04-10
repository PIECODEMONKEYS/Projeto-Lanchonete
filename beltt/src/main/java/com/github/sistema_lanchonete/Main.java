package com.github.sistema_lanchonete;


import com.github.sistema_lanchonete.config.CustomizerFactory;
import com.github.sistema_lanchonete.config.FlyWayConfig;
import com.github.sistema_lanchonete.config.HibernateConfig;
import com.github.sistema_lanchonete.entity.IngredienteEntity;
import com.github.sistema_lanchonete.repositories.EstoqueBD;
import com.github.sistema_lanchonete.repositories.IngredienteRepository;
import com.github.sistema_lanchonete.service.EstoqueService;
import jakarta.persistence.EntityManager;

public class Main {
    @SuppressWarnings("GrazieInspectionRunner")
    public static void main(String[] args) {
        EntityManager em = CustomizerFactory.getEntityManager();

        // 1️⃣ Migra o banco
        FlyWayConfig.migrate();

        // 2️⃣ Executa regra de negócio
        EstoqueBD estoque = new EstoqueBD();
        estoque.cadastrarIngrediente("Presunto", 40);

        // 3️⃣ Encerra recursos
        HibernateConfig.shutdown();

        System.out.println("Aplicação finalizada ✅");


        em.close();
        CustomizerFactory.fechar();
    }
    static void mainDoYuji()
    {
        // 1. Abre a conexão
        EntityManager em = CustomizerFactory.getEntityManager();
        IngredienteRepository repo = new IngredienteRepository(em);

        // 2. Cria e salva um ingrediente novo
        IngredienteEntity novo = new IngredienteEntity();
        novo.setNome("Pão de Hamburguer");
        novo.setEstoque(50);
        novo.setFrequencia("DIARIO"); //boa colocar frequencia no BD

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
