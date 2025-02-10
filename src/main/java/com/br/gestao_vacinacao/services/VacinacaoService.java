package com.br.gestao_vacinacao.services;

import com.br.gestao_vacinacao.model.entities.Paciente;
import com.br.gestao_vacinacao.model.entities.Vacina;
import com.br.gestao_vacinacao.model.entities.Vacinacao;
import com.br.gestao_vacinacao.repositories.PacienteRepository;
import com.br.gestao_vacinacao.repositories.VacinacaoRepository;

import java.util.Date;

public class VacinacaoService {
    private final VacinacaoRepository vacinacaoRepository;
    private final PacienteRepository pacienteRepository;

    public VacinacaoService(VacinacaoRepository vacinacaoRepository, PacienteRepository pacienteRepository) {
        this.vacinacaoRepository = vacinacaoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente buscarPacientePorCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("O CPF não pode ser vazio.");
        }

        Paciente paciente = pacienteRepository.findByCpf(cpf);
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente não encontrado com esse CPF.");
        }

        return paciente;
    }

    public void registrarVacinacao(Paciente paciente, Vacina vacina, int dose) {
        if (paciente == null || vacina == null) {
            throw new IllegalArgumentException("Paciente ou vacina não podem ser nulos.");
        }

        Vacinacao vacinacao = new Vacinacao();
        vacinacao.setPaciente(paciente);
        vacinacao.setDose(dose);
        vacinacao.setDataAplicacao(new Date());
        vacinacao.setVacina(vacina);

        vacinacaoRepository.save(vacinacao);
    }
}
