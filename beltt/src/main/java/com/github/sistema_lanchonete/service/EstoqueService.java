package com.github.sistema_lanchonete.service;

import com.github.sistema_lanchonete.entity.Ingrediente;
import com.github.sistema_lanchonete.repositories.IngredienteRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstoqueService {
    private IngredienteRepository repository;
    private Map<String, Ingrediente> cache = new HashMap<>();

    public EstoqueService(IngredienteRepository repository) {
        this.repository = repository;
        sincronizarCache();
    }

    private void sincronizarCache() {
        List<Ingrediente> lista = repository.buscarTodos();
        for (Ingrediente ingrediente : lista) {
            cache.put(ingrediente.getNome(), ingrediente);
        }
    }

    public Ingrediente buscarRapidoPorNome(String nome) {
        return cache.get(nome);
    }
}