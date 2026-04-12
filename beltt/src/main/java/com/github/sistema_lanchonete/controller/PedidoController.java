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

        // 1. Lista os produtos
        var produtos = produtoRepo.findAll();
        if (produtos.isEmpty()) {
            System.out.println("❌ Cadastre produtos no cardápio primeiro!");
            return;
        }

        produtos.forEach(p ->
                System.out.printf("[%d] %s - R$ %.2f%n",
                        p.getId(), p.getNome(), p.getPreco())
        );

        int idProd = LeitoresController.lerInteiro("ID do Produto desejado: ");

        var prodSelecionado = produtos.stream()
                .filter(p -> p.getId().equals((long) idProd))
                .findFirst()
                .orElse(null);

        if (prodSelecionado != null) {

            // 2. Atualiza o caixa
            caixa.registrarVenda(prodSelecionado.getPreco().doubleValue());

            // 3. Cria e salva pedido
            PedidoEntity pedido = new PedidoEntity();
            pedido.setValorTotal(prodSelecionado.getPreco().doubleValue());
            pedidoRepo.salvar(pedido);

            // 🔥 4. PAGAMENTO (ETAPA 14)
            System.out.println("Total do pedido: " + pedido.getValorTotal());

            System.out.println("Escolha o método de pagamento:");
            System.out.println("1 - CREDITO");
            System.out.println("2 - DEBITO");
            System.out.println("3 - PIX");
            System.out.println("4 - DINHEIRO");

            int opcao = LeitoresController.lerInteiro("Opção: ");

            String metodo = switch (opcao) {
                case 1 -> "CREDITO";
                case 2 -> "DEBITO";
                case 3 -> "PIX";
                case 4 -> "DINHEIRO";
                default -> throw new IllegalArgumentException("Método inválido");
            };

            System.out.println("💳 Método escolhido: " + metodo);

            // 5. Baixa estoque
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
                estoqueRepo.create(item);

                System.out.println("📉 Estoque de " + item.getNome() + " reduzido em 1.");
            }
        }
    }
}