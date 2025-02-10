package com.br.gestao_vacinacao.views;

import com.br.gestao_vacinacao.controllers.PacienteController;
import com.br.gestao_vacinacao.model.entities.Paciente;
import com.br.gestao_vacinacao.repositories.PacienteRepository;
import com.br.gestao_vacinacao.services.PacienteService;

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
    private final PacienteController controller;

    public PacienteEditor(String operacao, PacienteRepository repository) {
        setContentPane(contentPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        PacienteService pacienteService = new PacienteService(repository);
        this.controller = new PacienteController(pacienteService);

        if (operacao.equals("CADASTRAR")) {
            btPesquisar.setVisible(false);
            paciente = new Paciente();
        } else {
            alterarVisibilidadeDosCampos(false);
            configurarBotaoPesquisar();
        }

        configurarBotaoSalvar();
        configurarBotaoCancelar();
    }

    private void configurarBotaoPesquisar() {
        btPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = txtCpf.getText();
                paciente = controller.buscarPaciente(cpf);

                if (paciente != null) {
                    alterarVisibilidadeDosCampos(true);
                    preencherCampos(paciente);
                }
            }
        });
    }

    private void configurarBotaoSalvar() {
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (paciente == null) {
                    paciente = new Paciente();
                }

                paciente.setNome(txtNome.getText());
                paciente.setEndereco(txtEndereco.getText());
                paciente.setTelefone(txtTelefone.getText());
                paciente.setEmail(txtEmail.getText());
                paciente.setCpf(txtCpf.getText());

                if(controller.salvarPaciente(paciente)){
                    dispose();
                }
            }
        });
    }

    private void configurarBotaoCancelar() {
        btCancelar.addActionListener(e -> dispose());
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

    private void preencherCampos(Paciente paciente) {
        txtEmail.setText(paciente.getEmail());
        txtEndereco.setText(paciente.getEndereco());
        txtNome.setText(paciente.getNome());
        txtTelefone.setText(paciente.getTelefone());
    }
}