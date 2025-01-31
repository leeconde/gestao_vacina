package com.br.gestao_vacinacao;

import javax.swing.*;

public class MenuVacinacao extends JFrame {
    private JPanel contentPanel;
    private JTextField txtCpf;
    private JButton btPesquisar;
    private JTextField txtNome;
    private JComboBox cbVacina;
    private JTextField txtDose;
    private JButton btRegistrar;
    private JButton btCancelar;
    private JLabel lbVacinacao;
    private JLabel lbCpf;
    private JLabel lbNome;
    private JLabel lbVacina;
    private JLabel lbDose;

    public MenuVacinacao(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(contentPanel);
        setSize(400, 300);
        setLocationRelativeTo(null);
    }
}
