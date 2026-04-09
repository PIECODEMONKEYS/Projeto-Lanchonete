package com.github.codemonkeys;

import com.github.codemonkeys.DTO.PagamentoDTO;
import com.github.codemonkeys.service.PagamentoService;
import com.github.codemonkeys.service.PagamentoServiceImpl;
import com.github.codemonkeys.controller.PagamentoController;

public class Main {
    static void main(String[] args) {
        PagamentoService pagamentoService = new PagamentoServiceImpl();
        PagamentoController pagamentoController = new PagamentoController(pagamentoService);

        PagamentoDTO pagamentoPix = new PagamentoDTO(100.0, "pix");
        PagamentoDTO pagamentoCredito = new PagamentoDTO(100.0,"credito");
        PagamentoDTO pagamentoDebito = new PagamentoDTO(100.0, "debito");
        PagamentoDTO pagamentoDinheiro = new PagamentoDTO(100.0, "dinheiro");

        System.out.println("pix: " + pagamentoController.realizarPagamento(pagamentoPix));
        System.out.println("credito: " + pagamentoController.realizarPagamento(pagamentoCredito));
        System.out.println("debito: " + pagamentoController.realizarPagamento(pagamentoDebito));
        System.out.println("dinheiro: " + pagamentoController.realizarPagamento(pagamentoDinheiro));
    }
}
