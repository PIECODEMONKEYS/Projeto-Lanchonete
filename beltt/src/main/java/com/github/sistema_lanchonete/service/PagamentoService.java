package com.github.sistema_lanchonete.service;


import com.github.sistema_lanchonete.DTO.PagamentoDTO;

public interface PagamentoService {
    double processarPagamento(PagamentoDTO pagamentoDTO);
}
