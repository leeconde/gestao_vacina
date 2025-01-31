package com.br.gestao_vacinacao;

import com.br.gestao_vacinacao.entidades.Fabricante;
import com.br.gestao_vacinacao.repositorios.FabricanteRepository;
import org.springframework.dao.DataIntegrityViolationException;

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

    public FabricanteEditor(String operacao, FabricanteRepository repository) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(contentPanel);
        setSize(400, 300);
        setLocationRelativeTo(null);

        if (operacao.equals("CADASTRAR")) {
            btPesquisar.setVisible(false);

            fabricante = new Fabricante();
        } else {
            alterarVisibilidadeDosCampos(false);

            btPesquisar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cnpj = txtCnpj.getText();

                    if (cnpj.isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "O CNPJ não pode ser vazio",
                                "Aviso",
                                JOptionPane.WARNING_MESSAGE);
                    }

                    fabricante = repository.findByCnpj(cnpj);

                    if (fabricante == null) {
                        JOptionPane.showMessageDialog(null,
                                "Fabricante não encontrado com esse CNPJ, tente novamente.",
                                "Aviso",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        alterarVisibilidadeDosCampos(true);

                        preencherCampos(fabricante);
                    }
                }
            });
        }

        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                String cnpj = txtCnpj.getText();

                if (validarCamposVazios()) {
                    lancarMensagemCampoVazio();
                    return;
                }
                fabricante.setNome(nome);
                fabricante.setCnpj(cnpj);

                try {
                    repository.save(fabricante);
                } catch (DataIntegrityViolationException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Esse CNPJ já está cadastrado.",
                            "Aviso",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                dispose();
            }
        });


        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }

    private void alterarVisibilidadeDosCampos(boolean flag) {
        txtNome.setVisible(flag);
        lbNome.setVisible(flag);
    }

    public void preencherCampos(Fabricante fabricante) {
        txtNome.setText(fabricante.getNome());
    }

    public boolean validarCamposVazios() {
        boolean vazio = false;

        if (txtNome.getText().isEmpty()) {
            vazio = true;
        }
        return vazio;
    }

    public void lancarMensagemCampoVazio() {
        JOptionPane.showMessageDialog(null,
                "Os campos precisam ser preenchidos.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
    }
}
