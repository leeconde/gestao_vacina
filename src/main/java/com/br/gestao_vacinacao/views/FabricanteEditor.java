package com.br.gestao_vacinacao.views;

import com.br.gestao_vacinacao.controllers.FabricanteController;
import com.br.gestao_vacinacao.model.entities.Fabricante;
import com.br.gestao_vacinacao.repositories.FabricanteRepository;
import com.br.gestao_vacinacao.services.FabricanteService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FabricanteEditor extends JFrame {
    private JPanel contentPanel;
    private JTextField txtCnpj;
    private JButton btPesquisar;
    private JTextField txtNome;
    private JButton btSalvar;
    private JButton btCancelar;
    private JLabel lbCnpj;
    private JLabel lbNome;

    private Fabricante fabricante;
    private final FabricanteController controller;


    public FabricanteEditor(String operacao, FabricanteRepository repository) {
        setContentPane(contentPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        FabricanteService fabricanteService = new FabricanteService(repository);
        this.controller = new FabricanteController(fabricanteService);

        if (operacao.equals("CADASTRAR")) {
            btPesquisar.setVisible(false);
            fabricante = new Fabricante();
        } else {
            alterarVisibilidadeDosCampos(false);
            configurarBotaoPesquisar();
        }

        configurarBotaoSalvar();
        configurarBotaoCancelar();
    }

    private void configurarBotaoPesquisar() {
        btPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cnpj = txtCnpj.getText();
                fabricante = controller.buscarFabricante(cnpj);

                if (fabricante != null) {
                    alterarVisibilidadeDosCampos(true);
                    preencherCampos(fabricante);
                }
            }
        });
    }

    private void configurarBotaoSalvar() {
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fabricante == null) {
                    fabricante = new Fabricante();
                }

                fabricante.setNome(txtNome.getText());
                fabricante.setCnpj(txtCnpj.getText());

                if (controller.salvarFabricante(fabricante)) {
                    dispose();
                }
            }
        });
    }

    private void configurarBotaoCancelar() {
        btCancelar.addActionListener(e -> dispose());
    }

    private void alterarVisibilidadeDosCampos(boolean flag) {
        txtNome.setVisible(flag);
        lbNome.setVisible(flag);
    }

    private void preencherCampos(Fabricante fabricante) {
        txtNome.setText(fabricante.getNome());
    }
}
