package com.github.sistema_lanchonete.service;

import com.github.sistema_lanchonete.entity.ItemPedido;
import com.github.sistema_lanchonete.entity.Pedidos;
import com.github.sistema_lanchonete.entity.Produtos;
import com.github.sistema_lanchonete.exceptions.CriacaoPedidoException;
import com.github.sistema_lanchonete.repositories.PedidosRepository;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class PedidosService {
    private EntityManager em;
    public PedidosService(EntityManager em) {
        this.em = em;
    }

    public Pedidos criarPedido(Map<Long, Integer> itensMap) {
        try {
            em.getTransaction().begin();

            Pedidos novoPedido = new Pedidos();
            novoPedido.setDataHora(LocalDateTime.now());

            BigDecimal total = BigDecimal.ZERO;

            for (Map.Entry<Long, Integer> entry : itensMap.entrySet()) {
                Long produtoId = entry.getKey();
                Integer quantidade = entry.getValue();

                Produtos produto = em.find(Produtos.class, produtoId);

                if (produto != null) {
                    ItemPedido item = new ItemPedido();
                    item.setPedido(novoPedido);
                    item.setProduto(produto);
                    item.setQuantidade(quantidade);

                    // Adiciona na lista do pedido (o Cascadetypoe vai salvar o item)
                    novoPedido.getItens().add(item);

                    BigDecimal subtotal = produto.getPreco()
                            .multiply(BigDecimal.valueOf(quantidade));
                    total = total.add(subtotal);
                }
            }

            novoPedido.setValorTotal(total.setScale(2, RoundingMode.HALF_UP));

            em.persist(novoPedido);
            em.getTransaction().commit();

            return novoPedido;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new CriacaoPedidoException("Erro ao criar pedido: " + e.getMessage());
        }
    }
}
