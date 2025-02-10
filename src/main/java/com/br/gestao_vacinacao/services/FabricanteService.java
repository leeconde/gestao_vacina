package com.br.gestao_vacinacao.services;

import com.br.gestao_vacinacao.model.entities.Fabricante;
import com.br.gestao_vacinacao.repositories.FabricanteRepository;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

public class FabricanteService {
    private final FabricanteRepository repository;

    public FabricanteService(FabricanteRepository repository) {
        this.repository = repository;
    }

    public void salvarFabricante(Fabricante fabricante) {
        if (fabricante == null || validarCamposVazios(fabricante)) {
            throw new IllegalArgumentException("Os campos precisam ser preenchidos.");
        }

        try {
            repository.save(fabricante);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Esse CNPJ já está cadastrado.");
        }
    }

    public Fabricante buscarFabricantePorCnpj(String cnpj) {
        if (cnpj == null || cnpj.trim().isEmpty()) {
            throw new IllegalArgumentException("O CNPJ não pode ser vazio.");
        }

        return Optional.ofNullable(repository.findByCnpj(cnpj))
                .orElseThrow(() -> new IllegalArgumentException("Fabricante não encontrado com esse CNPJ."));
    }

    private boolean validarCamposVazios(Fabricante fabricante) {
        return fabricante.getNome() == null || fabricante.getNome().isEmpty();
    }

}
