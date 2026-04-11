package com.github.sistema_lanchonete.service;


import com.github.sistema_lanchonete.DTO.PagamentoDTO;
import com.github.sistema_lanchonete.entity.PagamentoEntity;

public interface PagamentoService {
    PagamentoEntity processarPagamento(PagamentoDTO pagamentoDTO);
}
