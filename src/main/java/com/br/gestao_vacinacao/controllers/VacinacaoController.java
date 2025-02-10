package com.br.gestao_vacinacao.controllers;

import com.br.gestao_vacinacao.model.entities.Paciente;
import com.br.gestao_vacinacao.model.entities.Vacina;
import com.br.gestao_vacinacao.services.VacinacaoService;

import javax.swing.*;

public class VacinacaoController {
    private final VacinacaoService vacinacaoService;

    public VacinacaoController(VacinacaoService vacinacaoService) {
        this.vacinacaoService = vacinacaoService;
    }

    public Paciente buscarPaciente(String cpf) {
        try {
            return vacinacaoService.buscarPacientePorCpf(cpf);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

    public void registrarVacinacao(Paciente paciente, Vacina vacina, int dose) {
        try {
            vacinacaoService.registrarVacinacao(paciente, vacina, dose);
            JOptionPane.showMessageDialog(null, "Vacinação registrada com sucesso!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
