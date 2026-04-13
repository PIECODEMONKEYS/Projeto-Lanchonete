package com.github.sistema_lanchonete.service;


import com.github.sistema_lanchonete.DTO.PagamentoDTO;
import com.github.sistema_lanchonete.entity.Pagamento;

public interface PagamentoService {
    Pagamento processarPagamento(PagamentoDTO pagamentoDTO);
}
