package com.github.sistema_lanchonete.service;


import com.github.sistema_lanchonete.DTO.PagamentoDTO;
import com.github.sistema_lanchonete.exceptions.PagamentoIncorretoException;

public class PagamentoServiceImpl implements PagamentoService {
    @Override
    public double processarPagamento(PagamentoDTO pagamentoDTO){
        if (pagamentoDTO == null){
            throw new PagamentoIncorretoException("PagamentoDTO nao pode ser nulo");
        }
        double valor = pagamentoDTO.getValor();
        String metodo = pagamentoDTO.getMetodoPagamento();

        if (valor <= 0){
            throw new PagamentoIncorretoException("o valor tem que ser maior que zero");
        }
        if (metodo == null || metodo.trim().isEmpty()){
            throw new PagamentoIncorretoException("Forma de pagamento invalida");
        }

        switch (metodo.trim().toLowerCase()){
            case "pix":
                return valor;

            case "credito":
                return valor * 1.05;

            case "debito":
                return valor * 1.02;

            case "dinheiro":
                return valor;

            default:
               throw new PagamentoIncorretoException("Metodo de pagamento nao existente");
        }
    }
}
