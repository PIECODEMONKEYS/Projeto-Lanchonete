package com.github.codemonkeys.controller;

import com.github.codemonkeys.DTO.PagamentoDTO;
import com.github.codemonkeys.service.PagamentoService;

public class PagamentoController {
    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService){
        this.pagamentoService = pagamentoService;
    }

    public double realizarPagamento(PagamentoDTO pagamentoDTO){
        return pagamentoService.processarPagamento(pagamentoDTO);
    }
}
