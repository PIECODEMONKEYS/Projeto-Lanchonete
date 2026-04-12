package com.github.sistema_lanchonete.service;

import com.github.sistema_lanchonete.DTO.PagamentoDTO;
import com.github.sistema_lanchonete.entity.PagamentoEntity;
import com.github.sistema_lanchonete.entity.Pedidos;
import com.github.sistema_lanchonete.exceptions.PagamentoIncorretoException;
import com.github.sistema_lanchonete.repositories.PagamentoRepository;
import com.github.sistema_lanchonete.repositories.PedidosRepository;
import com.github.sistema_lanchonete.service.metodo.MetodoPagamento;
import com.github.sistema_lanchonete.service.metodo.StatusPagamento;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class PagamentoServiceImpl implements PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PedidosRepository pedidosRepository;

    public PagamentoServiceImpl(PagamentoRepository pagamentoRepository,
                                PedidosRepository pedidosRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.pedidosRepository = pedidosRepository;
    }

    @Override
    public PagamentoEntity processarPagamento(PagamentoDTO pagamentoDTO) {
        if (pagamentoDTO == null) {
            throw new PagamentoIncorretoException("PagamentoDTO não pode ser null");
        }

        if (pagamentoDTO.getPedidoId() == null) {
            throw new PagamentoIncorretoException("ID do pedido é obrigatório");
        }

        if (pagamentoDTO.getMetodoPagamento() == null || pagamentoDTO.getMetodoPagamento().isBlank()) {
            throw new PagamentoIncorretoException("Método de pagamento é obrigatório");
        }

        Pedidos pedido = pedidosRepository.findById(pagamentoDTO.getPedidoId());

        if (pedido == null) {
            throw new PagamentoIncorretoException("Pedido não encontrado");
        }

        if (pedido.getValorTotal() == null ||
                pedido.getValorTotal().compareTo(BigDecimal.ZERO) <= 0) {
            throw new PagamentoIncorretoException("O pedido não possui valor válido para pagamento");
        }

        MetodoPagamento metodo;
        try {
            metodo = MetodoPagamento.valueOf(
                    pagamentoDTO.getMetodoPagamento().trim().toUpperCase()
            );
        } catch (IllegalArgumentException e) {
            throw new PagamentoIncorretoException(
                    "Método de pagamento inválido: " + pagamentoDTO.getMetodoPagamento()
            );
        }

        BigDecimal valorOriginal = pedido.getValorTotal().setScale(2, RoundingMode.HALF_UP);
        BigDecimal taxa = calcularTaxa(valorOriginal, metodo);
        BigDecimal valorFinal = valorOriginal.add(taxa).setScale(2, RoundingMode.HALF_UP);

        PagamentoEntity pagamento = new PagamentoEntity();
        pagamento.setPedido(pedido);
        pagamento.setMetodo(metodo);
        pagamento.setValorOriginal(valorOriginal.setScale(2, RoundingMode.HALF_UP));
        pagamento.setTaxa(taxa);
        pagamento.setValorFinal(valorFinal);
        pagamento.setStatus(StatusPagamento.APROVADO);
        pagamento.setDataPagamento(LocalDateTime.now());

        pagamentoRepository.salvar(pagamento);

        return pagamento;
    }

    private BigDecimal calcularTaxa(BigDecimal valorOriginal, MetodoPagamento metodo) {
        return switch (metodo) {
            case PIX, DINHEIRO -> BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
            case DEBITO -> valorOriginal.multiply(new BigDecimal("0.02")).setScale(2, RoundingMode.HALF_UP);
            case CREDITO -> valorOriginal.multiply(new BigDecimal("0.05")).setScale(2, RoundingMode.HALF_UP);
        };
    }
}