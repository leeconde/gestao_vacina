package com.br.gestao_vacinacao;

import com.br.gestao_vacinacao.repositorios.VacinaRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuVacina extends JFrame {
    private JPanel contentPanel;
    private JButton btCadastrar;
    private JButton btEditar;
    private JLabel lbTela;

    public MenuVacina(VacinaRepository vacinaRepository) {

        btCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VacinaEditor vacinaEditor = new VacinaEditor("CADASTRAR", vacinaRepository);
                vacinaEditor.setVisible(true);
            }
        });

        btEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VacinaEditor vacinaEditor = new VacinaEditor("EDITAR", vacinaRepository);
                vacinaEditor.setVisible(true);
            }
        });
    }
}