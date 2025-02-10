package com.br.gestao_vacinacao.views;

import com.br.gestao_vacinacao.controllers.VacinacaoController;
import com.br.gestao_vacinacao.model.entities.Paciente;
import com.br.gestao_vacinacao.model.entities.Vacina;
import com.br.gestao_vacinacao.repositories.PacienteRepository;
import com.br.gestao_vacinacao.repositories.VacinaRepository;
import com.br.gestao_vacinacao.repositories.VacinacaoRepository;
import com.br.gestao_vacinacao.services.VacinacaoService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MenuVacinacao extends JFrame {
    private JPanel contentPanel;
    private JTextField txtCpf;
    private JButton btPesquisar;
    private JTextField txtNomePaciente;
    private JComboBox<Vacina> cbVacina;
    private JTextField txtDose;
    private JButton btRegistrar;
    private JButton btCancelar;
    private JLabel lbVacinacao;
    private JLabel lbCpf;
    private JLabel lbNomePaciente;
    private JLabel lbVacina;
    private JLabel lbDose;

    private final VacinacaoController controller;
    private Paciente paciente;
    private Vacina vacinaSelecionada;

    public MenuVacinacao(VacinacaoRepository vacinacaoRepository, VacinaRepository vacinaRepository, PacienteRepository pacienteRepository) {
        setContentPane(contentPanel);
        setTitle("Menu Vacinacao");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        txtNomePaciente.setEditable(false);

        VacinacaoService vacinacaoService = new VacinacaoService(vacinacaoRepository, pacienteRepository);
        this.controller = new VacinacaoController(vacinacaoService);

        carregarVacinas(vacinaRepository);
        configurarBotaoPesquisar();
        configurarBotaoRegistrar();
        configurarBotaoCancelar();
    }

    private void configurarBotaoPesquisar() {
        btPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = txtCpf.getText();
                paciente = controller.buscarPaciente(cpf);

                if (paciente != null) {
                    txtNomePaciente.setText(paciente.getNome());
                }
            }
        });
    }

    private void configurarBotaoRegistrar() {
        btRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCamposVazios()) {
                    lancarMensagemCampoVazio();
                    return;
                }

                String doseText = txtDose.getText();
                int dose = Integer.parseInt(doseText);

                controller.registrarVacinacao(paciente, vacinaSelecionada, dose);
                dispose();
            }
        });
    }

    private void configurarBotaoCancelar() {
        btCancelar.addActionListener(e -> dispose());
    }

    public boolean validarCamposVazios() {
        return txtDose.getText().isEmpty();
    }

    public void lancarMensagemCampoVazio() {
        JOptionPane.showMessageDialog(null, "Os campos precisam ser preenchidos.", "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    public void carregarVacinas(VacinaRepository repository) {
        cbVacina.removeAllItems();
        List<Vacina> vacinas = repository.findAll();
        for (Vacina vacina : vacinas) {
            cbVacina.addItem(vacina);
        }

        cbVacina.addActionListener(ev -> {
            vacinaSelecionada = (Vacina) cbVacina.getSelectedItem();
        });
    }
}
