package com.github.codemonkeys.service;

import com.github.codemonkeys.DTO.PagamentoDTO;

public interface PagamentoService {
    double processarPagamento(PagamentoDTO pagamentoDTO);
}
