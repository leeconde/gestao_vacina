package com.br.gestao_vacinacao;

import com.br.gestao_vacinacao.entidades.Fabricante;
import com.br.gestao_vacinacao.entidades.Vacina;
import com.br.gestao_vacinacao.repositorios.FabricanteRepository;
import com.br.gestao_vacinacao.repositorios.VacinaRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private Fabricante fabricante;

    public VacinaEditor(VacinaRepository vacinaRepository, FabricanteRepository fabricanteRepository) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(contentPanel);
        setSize(400, 300);
        setLocationRelativeTo(null);

        txtNomeFabricante.setEditable(false);

        btPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cnpj = txtCnpjFabricante.getText();

                if (cnpj.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "O CNPJ não pode ser vazio",
                            "Aviso",
                            JOptionPane.WARNING_MESSAGE);
                }

                fabricante = fabricanteRepository.findByCnpj(cnpj);

                if (fabricante == null) {
                    JOptionPane.showMessageDialog(null,
                            "Fabricante não encontrado com esse CNPJ, tente novamente.",
                            "Aviso",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                txtNomeFabricante.setText(fabricante.getNome());
            }
        });

        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeVacina = txtNomeVacina.getText();
                String dateString = txtDataValidade.getText();
                Date data;
                try {
                    if (dateString.trim().isEmpty() || dateString.contains("_")) {
                        JOptionPane.showMessageDialog(null,
                                "Preencha a data corretamente! O formato valido é dd/MM/yyyy",
                                "Aviso",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    format.setLenient(false);
                    data = format.parse(dateString);

                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null,
                            "O formato da data está inválido.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                vacina = new Vacina();
                vacina.setDataValidade(data);
                vacina.setNome(nomeVacina);
                vacina.setFabricante(fabricante);

                vacinaRepository.save(vacina);
                dispose();
            }
        });


    }
}
