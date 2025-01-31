package com.br.gestao_vacinacao;

import com.br.gestao_vacinacao.repositorios.FabricanteRepository;
import com.br.gestao_vacinacao.repositorios.PacienteRepository;
import com.br.gestao_vacinacao.repositorios.VacinaRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaMenu extends JFrame {
    private JPanel contentPanel;
    private JButton btPaciente;
    private JButton btFabricante;
    private JButton btVacinacao;
    private JButton btRelatorio;
    private JButton btVacina;


    public TelaMenu(PacienteRepository pacienteRepository, FabricanteRepository fabricanteRepository, VacinaRepository vacinaRepository) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(contentPanel);
        setSize(400, 300);
        setLocationRelativeTo(null);


        btPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuPaciente menuPaciente = new MenuPaciente(pacienteRepository);
                menuPaciente.setVisible(true);
            }
        });

        btFabricante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuFabricante menuFabricante = new MenuFabricante(fabricanteRepository);
                menuFabricante.setVisible(true);
            }
        });

        btVacinacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuVacinacao menuVacinacao = new MenuVacinacao();
                menuVacinacao.setVisible(true);
            }
        });

        btVacina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuVacina menuVacina = new MenuVacina(vacinaRepository, fabricanteRepository);
                menuVacina.setVisible(true);
            }
        });
    }
}
