package com.br.gestao_vacinacao;

import com.br.gestao_vacinacao.repositorios.FabricanteRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFabricante extends JFrame {
    private JPanel contentPanel;
    private JButton btCadastrar;
    private JButton btEditar;
    private JLabel lbFabricante;
    private JButton btVoltar;

    public MenuFabricante(FabricanteRepository fabricanteRepository) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(contentPanel);
        setSize(400, 300);
        setLocationRelativeTo(null);

        btCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FabricanteEditor fabricanteEditor = new FabricanteEditor("CADASTRAR", fabricanteRepository);
                fabricanteEditor.setVisible(true);
            }
        });

        btEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FabricanteEditor fabricanteEditor = new FabricanteEditor("EDITAR", fabricanteRepository);
                fabricanteEditor.setVisible(true);
            }
        });

        btVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
