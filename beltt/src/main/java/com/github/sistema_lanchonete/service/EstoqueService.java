package com.github.sistema_lanchonete.service;

import com.github.sistema_lanchonete.entity.IngredienteEntity;
import com.github.sistema_lanchonete.repositories.IngredienteRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstoqueService {
    private IngredienteRepository repository;
    private Map<String, IngredienteEntity> cache = new HashMap<>();

    public EstoqueService(IngredienteRepository repository) {
        this.repository = repository;
        sincronizarCache();
    }

    private void sincronizarCache() {
        List<IngredienteEntity> lista = repository.buscarTodos();
        for (IngredienteEntity ingrediente : lista) {
            cache.put(ingrediente.getNome(), ingrediente);
        }
    }

    public IngredienteEntity buscarRapidoPorNome(String nome) {
        return cache.get(nome);
    }
}