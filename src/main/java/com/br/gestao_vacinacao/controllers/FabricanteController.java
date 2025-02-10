package com.br.gestao_vacinacao.controllers;

import com.br.gestao_vacinacao.model.entities.Fabricante;
import com.br.gestao_vacinacao.services.FabricanteService;

import javax.swing.*;

public class FabricanteController {

    private final FabricanteService fabricanteService;

    public FabricanteController(FabricanteService fabricanteService) {
        this.fabricanteService = fabricanteService;
    }

    public Fabricante buscarFabricante(String cnpj) {
        try {
            return fabricanteService.buscarFabricantePorCnpj(cnpj);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

    public boolean salvarFabricante(Fabricante fabricante) {
        try {
            fabricanteService.salvarFabricante(fabricante);
            JOptionPane.showMessageDialog(null, "Fabricante cadastrado com sucesso!");
            return true;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
