package com.br.gestao_vacinacao;

import com.br.gestao_vacinacao.entidades.Paciente;
import com.br.gestao_vacinacao.entidades.Vacina;
import com.br.gestao_vacinacao.entidades.Vacinacao;
import com.br.gestao_vacinacao.repositorios.PacienteRepository;
import com.br.gestao_vacinacao.repositorios.VacinaRepository;
import com.br.gestao_vacinacao.repositorios.VacinacaoRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class MenuVacinacao extends JFrame {
    private JPanel contentPanel;
    private JTextField txtCpf;
    private JButton btPesquisar;
    private JTextField txtNomePaciente;
    private JComboBox cbVacina;
    private JTextField txtDose;
    private JButton btRegistrar;
    private JButton btCancelar;
    private JLabel lbVacinacao;
    private JLabel lbCpf;
    private JLabel lbNomePaciente;
    private JLabel lbVacina;
    private JLabel lbDose;

    private Paciente paciente;
    private Vacina vacinaSelecionada;

    public MenuVacinacao(VacinacaoRepository vacinacaoRepository, VacinaRepository vacinaRepository, PacienteRepository pacienteRepository) {
        setContentPane(contentPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        txtNomePaciente.setEditable(false);

        carregarVacinas(vacinaRepository);

        cbVacina.addActionListener(ev -> {
            Object itemSelecionado = cbVacina.getSelectedItem();
            if (itemSelecionado instanceof Vacina) {
                vacinaSelecionada = (Vacina) itemSelecionado;
            }
        });


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

                paciente = pacienteRepository.findByCpf(cpf);

                if (paciente == null) {
                    JOptionPane.showMessageDialog(null,
                            "Paciente não encontrado com esse CPF, tente novamente.",
                            "Aviso",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    txtNomePaciente.setText(paciente.getNome());
                }
            }
        });


        btRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dose = txtDose.getText();


                if (validarCamposVazios()) {
                    lancarMensagemCampoVazio();
                    return;
                }

                Vacinacao vacinacao = new Vacinacao();
                vacinacao.setPaciente(paciente);
                vacinacao.setDose(Integer.valueOf(dose));
                vacinacao.setDataAplicacao(Date.from(Instant.now()));
                vacinacao.setVacina(vacinaSelecionada);

                try {
                    vacinacaoRepository.save(vacinacao);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            ex.getMessage(),
                            "Aviso",
                            JOptionPane.ERROR_MESSAGE);
                    System.out.println(ex.getMessage());
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

    public boolean validarCamposVazios() {
        return txtDose.getText().isEmpty();
    }

    public void lancarMensagemCampoVazio() {
        JOptionPane.showMessageDialog(null,
                "Os campos precisam ser preenchidos.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
    }

    public void carregarVacinas(VacinaRepository repository) {
        cbVacina.removeAllItems();
        List<Vacina> vacinas = repository.findAll();
        for (Vacina vacina : vacinas) {
            cbVacina.addItem(vacina);
        }
    }


}
