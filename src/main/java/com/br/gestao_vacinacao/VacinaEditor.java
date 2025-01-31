package com.br.gestao_vacinacao;

import com.br.gestao_vacinacao.entidades.Vacina;
import com.br.gestao_vacinacao.repositorios.VacinaRepository;

import javax.swing.*;

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

    private Vacina vacina;

    public VacinaEditor(String operacao, VacinaRepository vacinaRepository) {

    }
}
