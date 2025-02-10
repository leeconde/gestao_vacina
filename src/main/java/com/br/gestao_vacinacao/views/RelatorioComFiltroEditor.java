package com.br.gestao_vacinacao.views;

import com.br.gestao_vacinacao.model.entities.*;
import com.br.gestao_vacinacao.repositories.FabricanteRepository;
import com.br.gestao_vacinacao.repositories.PacienteRepository;
import com.br.gestao_vacinacao.repositories.VacinaRepository;
import com.br.gestao_vacinacao.repositories.VacinacaoRepository;
import com.br.gestao_vacinacao.views.modelos.RelatorioCadastroFabricanteTableModel;
import com.br.gestao_vacinacao.views.modelos.RelatorioCadastroPacienteTableModel;
import com.br.gestao_vacinacao.views.modelos.RelatorioCadastroVacinaTableModel;
import com.br.gestao_vacinacao.views.modelos.RelatorioVacinacoesTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RelatorioComFiltroEditor extends JFrame {

    private JPanel contentPanel;
    private JTextField txtFiltro;
    private JButton btPesquisar;
    private JTable table;
    private RelatorioVacinacoesTableModel relatorioVacinacoesTableModel;
    private RelatorioCadastroPacienteTableModel relatorioCadastroPacienteTableModel;
    private RelatorioCadastroFabricanteTableModel relatorioCadastroFabricanteTableModel;
    private RelatorioCadastroVacinaTableModel relatorioCadastroVacinaTableModel;
    private JLabel lbFiltro;
    private JButton btVoltar;

    private final String RELATORIO_VACINACOES_POR_CLIENTE = "RELATORIO_VACINACOES_POR_CLIENTE";
    private final String RELATORIO_VACINACOES_POR_VACINA = "RELATORIO_VACINACOES_POR_VACINA";
    private final String RELATORIO_TODAS_VACINACOES = "RELATORIO_TODAS_VACINACOES";
    private final String RELATORIO_CADASTRO_PACIENTE = "RELATORIO_CADASTRO_PACIENTE";
    private final String RELATORIO_CADASTRO_FABRICANTE = "RELATORIO_CADASTRO_FABRICANTE";
    private final String RELATORIO_CADASTRO_VACINA = "RELATORIO_CADASTRO_VACINA";

    public RelatorioComFiltroEditor(String operacao, VacinacaoRepository vacinacaoRepository,
                                    PacienteRepository pacienteRepository, FabricanteRepository
                                            fabricanteRepository, VacinaRepository vacinaRepository) {

        setContentPane(contentPanel);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        JPanel painelSuperior = new JPanel(new FlowLayout());
        lbFiltro = new JLabel();
        txtFiltro = new JTextField(15);
        btPesquisar = new JButton("Pesquisar");
        btVoltar = new JButton("Voltar");

        painelSuperior.add(lbFiltro);
        painelSuperior.add(txtFiltro);
        painelSuperior.add(btPesquisar);
        painelSuperior.add(btVoltar);

        add(painelSuperior, BorderLayout.NORTH);


        if (operacao.equals(RELATORIO_VACINACOES_POR_CLIENTE)) {
            lbFiltro.setText("CPF");

            relatorioVacinacoesTableModel = new RelatorioVacinacoesTableModel(new ArrayList<>());
            table = new JTable(relatorioVacinacoesTableModel);
            add(new JScrollPane(table), BorderLayout.CENTER);

            btPesquisar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String filtro = txtFiltro.getText().trim();

                    if (filtro.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Digite um CPF para pesquisar!",
                                "Aviso", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    Paciente paciente = pacienteRepository.findByCpf(filtro);
                    if (paciente == null) {
                        JOptionPane.showMessageDialog(null, "Paciente não encontrado!",
                                "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    List<Vacinacao> lista = vacinacaoRepository.findAllByPaciente(paciente);
                    List<RelatorioVacinacoes> dadosTabela = new ArrayList<>();

                    for (Vacinacao vacinacao : lista) {
                        RelatorioVacinacoes relatorioVacinacoes = new RelatorioVacinacoes();
                        relatorioVacinacoes.setNomePaciente(paciente.getNome());
                        relatorioVacinacoes.setTelefonePaciente(paciente.getTelefone());
                        relatorioVacinacoes.setEmailPaciente(paciente.getEmail());
                        relatorioVacinacoes.setNomeVacina(vacinacao.getVacina().getNome());
                        relatorioVacinacoes.setDoseVacina(vacinacao.getDose());
                        relatorioVacinacoes.setDataAplicacaoVacina(vacinacao.getDataAplicacao());
                        dadosTabela.add(relatorioVacinacoes);
                    }

                    relatorioVacinacoesTableModel.setDados(dadosTabela);

                }
            });
        }

        if (operacao.equals(RELATORIO_VACINACOES_POR_VACINA)) {
            lbFiltro.setText("Vacina");

            relatorioVacinacoesTableModel = new RelatorioVacinacoesTableModel(new ArrayList<>());
            table = new JTable(relatorioVacinacoesTableModel);
            add(new JScrollPane(table), BorderLayout.CENTER);

            btPesquisar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String filtro = txtFiltro.getText().trim();

                    if (filtro.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Digite uma vacina para pesquisar!",
                                "Aviso", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    Vacina vacina = vacinaRepository.findByNome(filtro);

                    if (vacina == null) {
                        JOptionPane.showMessageDialog(null, "Vacina não encontrada!",
                                "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    List<Vacinacao> lista = vacinacaoRepository.findAllByVacina(vacina);
                    List<RelatorioVacinacoes> dadosTabela = new ArrayList<>();

                    for (Vacinacao vacinacao : lista) {
                        RelatorioVacinacoes relatorioVacinacoes = new RelatorioVacinacoes();
                        relatorioVacinacoes.setNomePaciente(vacinacao.getPaciente().getNome());
                        relatorioVacinacoes.setTelefonePaciente(vacinacao.getPaciente().getTelefone());
                        relatorioVacinacoes.setEmailPaciente(vacinacao.getPaciente().getEmail());
                        relatorioVacinacoes.setNomeVacina(vacinacao.getVacina().getNome());
                        relatorioVacinacoes.setDoseVacina(vacinacao.getDose());
                        relatorioVacinacoes.setDataAplicacaoVacina(vacinacao.getDataAplicacao());
                        dadosTabela.add(relatorioVacinacoes);
                    }

                    relatorioVacinacoesTableModel.setDados(dadosTabela);

                }
            });
        }

        if (operacao.equals(RELATORIO_TODAS_VACINACOES)) {
            lbFiltro.setVisible(false);
            txtFiltro.setVisible(false);
            btPesquisar.setVisible(false);

            relatorioVacinacoesTableModel = new RelatorioVacinacoesTableModel(new ArrayList<>());
            table = new JTable(relatorioVacinacoesTableModel);
            add(new JScrollPane(table), BorderLayout.CENTER);


            List<Vacinacao> lista = vacinacaoRepository.findAll();
            List<RelatorioVacinacoes> dadosTabela = new ArrayList<>();

            for (Vacinacao vacinacao : lista) {
                RelatorioVacinacoes relatorioVacinacoes = new RelatorioVacinacoes();
                relatorioVacinacoes.setNomePaciente(vacinacao.getPaciente().getNome());
                relatorioVacinacoes.setTelefonePaciente(vacinacao.getPaciente().getTelefone());
                relatorioVacinacoes.setEmailPaciente(vacinacao.getPaciente().getEmail());
                relatorioVacinacoes.setNomeVacina(vacinacao.getVacina().getNome());
                relatorioVacinacoes.setDoseVacina(vacinacao.getDose());
                relatorioVacinacoes.setDataAplicacaoVacina(vacinacao.getDataAplicacao());
                dadosTabela.add(relatorioVacinacoes);
            }

            relatorioVacinacoesTableModel.setDados(dadosTabela);
        }

        if (operacao.equals(RELATORIO_CADASTRO_PACIENTE)) {
            lbFiltro.setVisible(false);
            txtFiltro.setVisible(false);
            btPesquisar.setVisible(false);
            relatorioCadastroPacienteTableModel = new RelatorioCadastroPacienteTableModel(new ArrayList<>());
            table = new JTable(relatorioCadastroPacienteTableModel);
            add(new JScrollPane(table), BorderLayout.CENTER);

            List<Paciente> pacientes = pacienteRepository.findAll();

            relatorioCadastroPacienteTableModel.setDados(pacientes);
        }

        if (operacao.equals(RELATORIO_CADASTRO_FABRICANTE)) {
            lbFiltro.setVisible(false);
            txtFiltro.setVisible(false);
            btPesquisar.setVisible(false);
            relatorioCadastroFabricanteTableModel = new RelatorioCadastroFabricanteTableModel(new ArrayList<>());
            table = new JTable(relatorioCadastroFabricanteTableModel);
            add(new JScrollPane(table), BorderLayout.CENTER);

            List<Fabricante> fabricantes = fabricanteRepository.findAll();

            relatorioCadastroFabricanteTableModel.setDados(fabricantes);
        }

        if (operacao.equals(RELATORIO_CADASTRO_VACINA)) {
            lbFiltro.setVisible(false);
            txtFiltro.setVisible(false);
            btPesquisar.setVisible(false);
            relatorioCadastroVacinaTableModel = new RelatorioCadastroVacinaTableModel(new ArrayList<>());
            table = new JTable(relatorioCadastroVacinaTableModel);
            add(new JScrollPane(table), BorderLayout.CENTER);

            List<Vacina> vacinas = vacinaRepository.findAll();
            List<RelatorioVacinas> dadosTabela = new ArrayList<>();

            for (Vacina vacina : vacinas) {
                RelatorioVacinas relatorio = new RelatorioVacinas();
                relatorio.setId(vacina.getId());
                relatorio.setNome(vacina.getNome());
                relatorio.setValidade(vacina.getDataValidade());
                relatorio.setNomeFabricante(vacina.getFabricante().getNome());
                dadosTabela.add(relatorio);
            }

            relatorioCadastroVacinaTableModel.setDados(dadosTabela);
        }

        btVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }


}
