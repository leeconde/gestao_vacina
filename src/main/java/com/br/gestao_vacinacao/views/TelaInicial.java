package com.br.gestao_vacinacao.views;

import com.br.gestao_vacinacao.repositories.FabricanteRepository;
import com.br.gestao_vacinacao.repositories.PacienteRepository;
import com.br.gestao_vacinacao.repositories.VacinaRepository;
import com.br.gestao_vacinacao.repositories.VacinacaoRepository;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicial extends JFrame {

    private JPanel contentPanel;
    private JLabel lbTitulo;
    private JButton btEntrar;

    public TelaInicial(PacienteRepository pacienteRepository, FabricanteRepository fabricanteRepository, VacinaRepository vacinaRepository, VacinacaoRepository vacinacaoRepository) {

        setTitle("Tela Inicial");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        ImageIcon imagem = new ImageIcon(getClass().getResource("/gestao_vacinacao_logo.png"));
        JLabel labelImagem = new JLabel(imagem);
        labelImagem.setHorizontalAlignment(SwingConstants.CENTER);

        lbTitulo = new JLabel("Gestão de Vacinação");
        lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitulo.setFont(new Font("Arial", Font.BOLD, 16));

        btEntrar = new JButton("Entrar");
        btEntrar.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new GridLayout(2, 1, 10, 10));
        panelInferior.setBorder(new EmptyBorder(0, 10, 20, 10));
        panelInferior.add(lbTitulo);
        panelInferior.add(btEntrar);


        contentPanel.add(labelImagem, BorderLayout.CENTER);
        contentPanel.add(panelInferior, BorderLayout.SOUTH);

        add(contentPanel);

        setVisible(true);

        btEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaMenu telaMenu = new TelaMenu(pacienteRepository,
                        fabricanteRepository,
                        vacinaRepository,
                        vacinacaoRepository);
                telaMenu.setVisible(true);
            }
        });
    }

}
