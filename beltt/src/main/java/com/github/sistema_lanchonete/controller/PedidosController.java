package com.github.sistema_lanchonete.controller;

import com.github.sistema_lanchonete.entity.Pedidos;
import com.github.sistema_lanchonete.repositories.PedidosRepository;
import com.github.sistema_lanchonete.service.PedidosService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PedidosController {
    private final PedidosRepository repository;
    private final PedidosService service;

    private Map<Long, Integer> itensCarrinho = new HashMap<>();

    public PedidosController(PedidosRepository repository, PedidosService service) {
        this.repository = repository;
        this.service = service;
    }

    public void adicionarItem(Long produtoId, Integer quantidade) {
        if (quantidade <= 0) return;

        itensCarrinho.merge(produtoId, quantidade, Integer::sum);
        System.out.println("Produto " + produtoId + " adicionado. Quantidade atual: " + itensCarrinho.get(produtoId));
    }

    public void finalizarPedido() {
        if (itensCarrinho.isEmpty()) {
            System.out.println("Carrinho vazio!");
            return;
        }

        try {
            Pedidos pedidoCriado = service.criarPedido(itensCarrinho);
            itensCarrinho.clear(); // Limpa o carrinho após o sucesso
            System.out.println("Pedido realizado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao finalizar: " + e.getMessage());
        }
    }
    public void procurarPorData(Scanner sc)
    {
        Integer ano = LeitoresController.lerInteiro(sc);
        Integer mes = LeitoresController.lerInteiro(sc);
        Integer dia = LeitoresController.lerInteiro(sc);

        try {
            List<Pedidos> pedidosEncontrados = repository.ProcurarDia(ano, mes, dia);

            if (pedidosEncontrados.isEmpty()) {
                System.out.println("Nenhum pedido encontrado para a data " + dia + "/" + mes + "/" + ano);
            } else {
                System.out.println("\nPedidos encontrados:");
                for (Pedidos p : pedidosEncontrados) {
                    System.out.println("ID: " + p.getId() +
                            " | Hora: " + p.getDataHora().toLocalTime() +
                            " | Total: R$ " + p.getValorTotal());
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar pedidos: " + e.getMessage());
        }
    }
}
