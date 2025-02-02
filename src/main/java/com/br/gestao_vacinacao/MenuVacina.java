package com.br.gestao_vacinacao;

import com.br.gestao_vacinacao.repositorios.FabricanteRepository;
import com.br.gestao_vacinacao.repositorios.VacinaRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuVacina extends JFrame {
    private JPanel contentPanel;
    private JButton btCadastrar;
    private JLabel lbTela;
    private JButton btVoltar;

    public MenuVacina(VacinaRepository vacinaRepository, FabricanteRepository fabricanteRepository) {
        setContentPane(contentPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        btCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VacinaEditor vacinaEditor = new VacinaEditor(vacinaRepository, fabricanteRepository);
                vacinaEditor.setVisible(true);
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