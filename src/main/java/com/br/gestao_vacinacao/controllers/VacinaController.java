package com.br.gestao_vacinacao.controllers;

import com.br.gestao_vacinacao.model.entities.Fabricante;
import com.br.gestao_vacinacao.services.VacinaService;

import javax.swing.*;

public class VacinaController {

    private final VacinaService vacinaService;

    public VacinaController(VacinaService vacinaService) {
        this.vacinaService = vacinaService;
    }

    public Fabricante buscarFabricante(String cnpj) {
        try {
            return vacinaService.buscarFabricantePorCnpj(cnpj);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

    public void salvarVacina(String nomeVacina, String dataValidade, Fabricante fabricante) {
        try {
            vacinaService.salvarVacina(nomeVacina, dataValidade, fabricante);
            JOptionPane.showMessageDialog(null, "Vacina cadastrada com sucesso!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
