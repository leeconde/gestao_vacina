package com.br.gestao_vacinacao;

import com.br.gestao_vacinacao.entidades.Paciente;
import com.br.gestao_vacinacao.repositorios.PacienteRepository;
import org.springframework.dao.DataIntegrityViolationException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PacienteEditor extends JFrame {

    private JPanel contentPanel;
    private JTextField txtNome;
    private JTextField txtEndereco;
    private JTextField txtTelefone;
    private JTextField txtEmail;
    private JLabel lbNome;
    private JLabel lbEndereco;
    private JLabel lbTelefone;
    private JLabel lbEmail;
    private JButton btSalvar;
    private JButton btCancelar;
    private JTextField txtCpf;
    private JButton btPesquisar;
    private JLabel lbCpf;

    private Paciente paciente;

    public PacienteEditor(String operacao, PacienteRepository repository) {
        setContentPane(contentPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        if (operacao.equals("CADASTRAR")) {
            btPesquisar.setVisible(false);

            paciente = new Paciente();
        } else {
            alterarVisibilidadeDosCampos(false);

            btPesquisar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String cpf = txtCpf.getText();

                    if (cpf.isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "O CPF não pode ser vazio",
                                "Aviso",
                                JOptionPane.WARNING_MESSAGE);
                    }

                    paciente = repository.findByCpf(cpf);

                    if (paciente == null) {
                        JOptionPane.showMessageDialog(null,
                                "Paciente não encontrado com esse CPF, tente novamente.",
                                "Aviso",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        alterarVisibilidadeDosCampos(true);

                        preencherCampos(paciente);
                    }
                }
            });
        }

        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                String telefone = txtTelefone.getText();
                String endereco = txtEndereco.getText();
                String email = txtEmail.getText();
                String cpf = txtCpf.getText();

                if (validarCamposVazios()) {
                    lancarMensagemCampoVazio();
                    return;
                }
                paciente.setNome(nome);
                paciente.setEndereco(endereco);
                paciente.setTelefone(telefone);
                paciente.setEmail(email);
                paciente.setCpf(cpf);

                try {
                    repository.save(paciente);
                } catch (DataIntegrityViolationException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Esse CPF já está cadastrado.",
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
        txtEndereco.setVisible(flag);
        txtEmail.setVisible(flag);
        txtTelefone.setVisible(flag);
        lbEmail.setVisible(flag);
        lbNome.setVisible(flag);
        lbEndereco.setVisible(flag);
        lbTelefone.setVisible(flag);
    }

    public void preencherCampos(Paciente paciente) {
        txtEmail.setText(paciente.getEmail());
        txtEndereco.setText(paciente.getEndereco());
        txtNome.setText(paciente.getNome());
        txtTelefone.setText(paciente.getTelefone());
    }

    public boolean validarCamposVazios() {
        boolean vazio = false;

        if (txtNome.getText().isEmpty()) {
            vazio = true;
        }

        if (txtEmail.getText().isEmpty()) {
            vazio = true;
        }

        if (txtTelefone.getText().isEmpty()) {
            vazio = true;
        }

        if (txtEndereco.getText().isEmpty()) {
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