package com.br.gestao_vacinacao.views;

import com.br.gestao_vacinacao.controllers.VacinaController;
import com.br.gestao_vacinacao.model.entities.Fabricante;
import com.br.gestao_vacinacao.repositories.FabricanteRepository;
import com.br.gestao_vacinacao.repositories.VacinaRepository;
import com.br.gestao_vacinacao.services.VacinaService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VacinaEditor extends JFrame {
    private JPanel contentPanel;
    private JTextField txtCnpjFabricante;
    private JButton btPesquisar;
    private JTextField txtNomeVacina;
    private JTextField txtNomeFabricante;
    private JLabel lbCnpjFabricante;
    private JLabel lbNomeFabricante;
    private JLabel lbNome;
    private JLabel lbValidade;
    private JFormattedTextField txtDataValidade;
    private JButton btSalvar;
    private JButton btCancelar;

    private final VacinaController controller;
    private Fabricante fabricante;

    public VacinaEditor(VacinaRepository vacinaRepository, FabricanteRepository fabricanteRepository) {
        setContentPane(contentPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        txtNomeFabricante.setEditable(false);

        VacinaService vacinaService = new VacinaService(vacinaRepository, fabricanteRepository);
        this.controller = new VacinaController(vacinaService);

        configurarBotaoPesquisar();
        configurarBotaoSalvar();
        configurarBotaoCancelar();
    }

    private void configurarBotaoPesquisar() {
        btPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cnpj = txtCnpjFabricante.getText();
                fabricante = controller.buscarFabricante(cnpj);

                if (fabricante != null) {
                    txtNomeFabricante.setText(fabricante.getNome());
                }
            }
        });
    }

    private void configurarBotaoSalvar() {
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeVacina = txtNomeVacina.getText();
                String dataValidade = txtDataValidade.getText();

                controller.salvarVacina(nomeVacina, dataValidade, fabricante);
                dispose();
            }
        });
    }

    private void configurarBotaoCancelar() {
        btCancelar.addActionListener(e -> dispose());
    }
}
