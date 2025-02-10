package com.br.gestao_vacinacao.services;

import com.br.gestao_vacinacao.model.entities.Paciente;
import com.br.gestao_vacinacao.repositories.PacienteRepository;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

public class PacienteService {

    private final PacienteRepository repository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.repository = pacienteRepository;
    }

    public void salvarPaciente(Paciente paciente) {
        if (paciente == null || validarCamposVazios(paciente)) {
            throw new IllegalArgumentException("Os campos precisam ser preenchidos.");
        }

        try {
            repository.save(paciente);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Esse CPF já está cadastrado.");
        }
    }

    public Paciente buscarPacientePorCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("O CPF não pode ser vazio.");
        }

        return Optional.ofNullable(repository.findByCpf(cpf))
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado com esse CPF."));
    }

    private boolean validarCamposVazios(Paciente paciente) {
        return paciente.getNome() == null || paciente.getNome().isEmpty()
                || paciente.getEmail() == null || paciente.getEmail().isEmpty()
                || paciente.getEndereco() == null || paciente.getEndereco().isEmpty()
                || paciente.getTelefone() == null || paciente.getTelefone().isEmpty();
    }
}
