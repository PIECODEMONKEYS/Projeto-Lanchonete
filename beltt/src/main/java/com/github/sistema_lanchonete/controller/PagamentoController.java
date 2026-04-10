package com.github.sistema_lanchonete.controller;


import com.github.sistema_lanchonete.DTO.PagamentoDTO;
import com.github.sistema_lanchonete.exceptions.PagamentoIncorretoException;
import com.github.sistema_lanchonete.service.PagamentoService;

public class PagamentoController {
    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService){
        this.pagamentoService = pagamentoService;
    }

    public double realizarPagamento(PagamentoDTO pagamentoDTO){
        try{
            return pagamentoService.processarPagamento(pagamentoDTO);
        } catch (PagamentoIncorretoException e)
        {
            System.out.println("Operacao cancelada: " + e.getMessage());
            return 0.0;
        }
    }
}
