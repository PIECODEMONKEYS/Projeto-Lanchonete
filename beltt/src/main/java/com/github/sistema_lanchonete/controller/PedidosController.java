package com.github.sistema_lanchonete.controller;

import com.github.sistema_lanchonete.DTO.PagamentoDTO;
import com.github.sistema_lanchonete.entity.Pagamento;
import com.github.sistema_lanchonete.entity.Pedidos;
import com.github.sistema_lanchonete.exceptions.PagamentoIncorretoException;
import com.github.sistema_lanchonete.repositories.PedidosRepository;
import com.github.sistema_lanchonete.service.PedidosService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PedidosController {
    private final PedidosRepository repository;
    private final PedidosService service;
    private final PagamentoController pagamentoController;
    private CaixaController caixaController;
    private Map<Long, Integer> itensCarrinho = new HashMap<>();

    public PedidosController(PedidosRepository repository,
                             PedidosService service,
                             PagamentoController pagamentoController, CaixaController caixaController) {
        this.repository = repository;
        this.service = service;
        this.pagamentoController = pagamentoController;
        this.caixaController = caixaController;
    }

    public void adicionarItem(Long produtoId, Integer quantidade) {
        if (quantidade <= 0) return;

        itensCarrinho.merge(produtoId, quantidade, Integer::sum);
        System.out.println("Produto " + produtoId + " adicionado. Quantidade atual: " + itensCarrinho.get(produtoId));
    }

    public void finalizarPedido(Scanner sc, CaixaController caixa) {
        if (itensCarrinho.isEmpty()) {
            System.out.println("Carrinho vazio!");
            return;
        }
        try{
            Pedidos pedidoCriado = service.criarPedido(itensCarrinho);

            System.out.println("pedido realizadp");
            System.out.println("Id: " + pedidoCriado.getId());
            System.out.println("Total a pagar:" + pedidoCriado.getValorTotal());

            System.out.println("Metodo de pagamento: ");
            System.out.println("1-credito");
            System.out.println("2-debito");
            System.out.println("3-pix");
            System.out.println("4-dinheiro");

            int opcao = LeitoresController.lerInteiro(sc);

            String metodo = switch (opcao){
                case 1 -> "CREDITO";
                case 2 -> "DEBITO";
                case 3 -> "PIX";
                case 4 -> "DINHEIRO";
                default -> throw new PagamentoIncorretoException("metodo nao existente");
            };

            PagamentoDTO dto = new PagamentoDTO();
            dto.setPedidoId(pedidoCriado.getId());
            dto.setMetodoPagamento(metodo);

            Pagamento pagamento = pagamentoController.realizarPagamento(dto);

            if (pagamento == null){
                System.out.println("erro ao processar pagamento");
                return;
            }
            itensCarrinho.clear();

            System.out.println("pagamento feito com sucesso");
            System.out.println("metodo: " + pagamento.getMetodo());
            System.out.println("valor final: " + pagamento.getValorFinal());
            System.out.println("status: " + pagamento.getStatus());
            caixa.registrarVenda(pagamento.getValorFinal());
            if(opcao == 4){caixa.adicionarDinheiro(pagamento.getValorFinal());}
        } catch (Exception e){
            System.out.println("erro ao finalizar: " + e.getMessage());
        }
    }

    public void fazerPedido(Scanner sc, CaixaController caixa) {
        itensCarrinho.clear();

        do{
            System.out.println("Digite o id do produto: ");
            long produtoId = LeitoresController.lerInteiro(sc);

            System.out.println("digite a quantidade: ");
            int quantidade = LeitoresController.lerInteiro(sc);

            adicionarItem(produtoId, quantidade);
            System.out.println("Adicionar produto" +
                    "\n1. sim \n2. nao");
            int opc = LeitoresController.lerInteiro(sc);
            if(opc == 2){ break; }
        } while (true);
        finalizarPedido(sc, caixa);
    }
}
