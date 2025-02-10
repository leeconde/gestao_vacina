package com.br.gestao_vacinacao.controllers;

import com.br.gestao_vacinacao.model.entities.Paciente;
import com.br.gestao_vacinacao.services.PacienteService;

import javax.swing.*;

public class PacienteController {
    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    public Paciente buscarPaciente(String cpf) {
        try {
            return pacienteService.buscarPacientePorCpf(cpf);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

    public boolean salvarPaciente(Paciente paciente) {
        try {
            pacienteService.salvarPaciente(paciente);
            JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso!");
            return true;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

}
