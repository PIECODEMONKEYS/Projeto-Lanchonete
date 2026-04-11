package com.github.sistema_lanchonete.controller;

import com.github.sistema_lanchonete.config.CustomizerFactory;
import com.github.sistema_lanchonete.entity.PedidoEntity;
import com.github.sistema_lanchonete.repositories.IngredienteRepository;
import com.github.sistema_lanchonete.repositories.PedidoRepository;
import com.github.sistema_lanchonete.repositories.ProdutosRepository;

public class PedidoController {
    private final CaixaController caixa;
    private final PedidoRepository pedidoRepo;
    private final ProdutosRepository produtoRepo;
    private final IngredienteRepository estoqueRepo;

    public PedidoController(CaixaController caixa) {
        this.caixa = caixa;
        var em = CustomizerFactory.getEntityManager();
        this.pedidoRepo = new PedidoRepository(em);
        this.produtoRepo = new ProdutosRepository(em);
        this.estoqueRepo = new IngredienteRepository(em);
    }

    public void novoPedido() {
        System.out.println("\n📝 --- LANÇAR NOVO PEDIDO ---");

        // 1. Lista os produtos do cardápio
        var produtos = produtoRepo.buscarTodos();
        if (produtos.isEmpty()) {
            System.out.println("❌ Cadastre produtos no cardápio primeiro!");
            return;
        }

        produtos.forEach(p -> System.out.printf("[%d] %s - R$ %.2f%n", p.getId(), p.getNome(), p.getPreco()));

        int idProd = LeitoresController.lerInteiro("ID do Produto desejado: ");
        var prodSelecionado = produtos.stream()
                .filter(p -> p.getId().equals((long) idProd))
                .findFirst().orElse(null);

        if (prodSelecionado != null) {
            // --- VALIDAÇÃO DE ESTOQUE (Sênior) ---
            // Verifica se há ingredientes para processar o pedido
            var ingredientes = estoqueRepo.buscarTodos();
            if (ingredientes.isEmpty() || ingredientes.get(0).getEstoque() <= 0) {
                System.out.println("❌ Erro: Ingredientes insuficientes no estoque! Venda cancelada.");
                return;
            }

            // 2. Atualiza o Caixa (Soma o valor)
            // Agora o getPreco() já retorna double, sem necessidade de conversão manual
            caixa.registrarVenda(prodSelecionado.getPreco());

            // 3. Salva o Pedido no Banco
            PedidoEntity pedido = new PedidoEntity();
            pedido.setValorTotal(prodSelecionado.getPreco());
            pedidoRepo.salvar(pedido);

            // 4. Baixa automática de 1 ingrediente (Simulação de estoque)
            baixarEstoqueSimbolico();

            System.out.println("✅ Pedido finalizado: " + prodSelecionado.getNome());
        } else {
            System.out.println("⚠️ Produto não encontrado!");
        }
    }

    private void baixarEstoqueSimbolico() {
        var ingredientes = estoqueRepo.buscarTodos();
        if (!ingredientes.isEmpty()) {
            var item = ingredientes.get(0);
            if (item.getEstoque() > 0) {
                item.setEstoque(item.getEstoque() - 1);
                // O método salvar agora existe no repositório e usa merge
                estoqueRepo.salvar(item);
                System.out.println("📉 Estoque de " + item.getNome() + " reduzido em 1.");
            }
        }
    }
}