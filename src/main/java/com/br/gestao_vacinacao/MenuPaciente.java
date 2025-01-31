package com.br.gestao_vacinacao;

import com.br.gestao_vacinacao.repositorios.PacienteRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPaciente extends JFrame {
    private JPanel contentPanel;
    private JButton btCadastrar;
    private JButton btEditar;
    private JLabel lbPaciente;
    private JButton btVoltar;
    private String operacao;

    public MenuPaciente(PacienteRepository pacienteRepository){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(contentPanel);
        setSize(400, 300);
        setLocationRelativeTo(null);

        btCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PacienteEditor pacienteEditor = new PacienteEditor("CADASTRAR", pacienteRepository);
                pacienteEditor.setVisible(true);
            }
        });

        btEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PacienteEditor pacienteEditor = new PacienteEditor("EDITAR", pacienteRepository);
                pacienteEditor.setVisible(true);
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
