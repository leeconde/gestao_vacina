package com.br.gestao_vacinacao;

import com.br.gestao_vacinacao.repositorios.FabricanteRepository;
import com.br.gestao_vacinacao.repositorios.PacienteRepository;
import com.br.gestao_vacinacao.repositorios.VacinaRepository;
import com.br.gestao_vacinacao.repositorios.VacinacaoRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuRelatorio extends JFrame {

    private JPanel contentPanel;
    private JButton btRelatorioVacinacoesPorPaciente;
    private JButton btRelatorioVacinacoesPorVacina;
    private JButton btTodasVacinacoes;
    private JLabel lbRelatorio;
    private JButton btTodasFabricantes;
    private JButton btTodasVacinas;
    private JButton btTodosPacientes;
    private JButton btVoltar;
    private JLabel lbRelatoriosCadastros;

    private final String RELATORIO_VACINACOES_POR_CLIENTE = "RELATORIO_VACINACOES_POR_CLIENTE";
    private final String RELATORIO_VACINACOES_POR_VACINA = "RELATORIO_VACINACOES_POR_VACINA";
    private final String RELATORIO_TODAS_VACINACOES = "RELATORIO_TODAS_VACINACOES";
    private final String RELATORIO_CADASTRO_PACIENTE = "RELATORIO_CADASTRO_PACIENTE";
    private final String RELATORIO_CADASTRO_FABRICANTE = "RELATORIO_CADASTRO_FABRICANTE";
    private final String RELATORIO_CADASTRO_VACINA = "RELATORIO_CADASTRO_VACINA";

    public MenuRelatorio(VacinacaoRepository vacinacaoRepository, PacienteRepository pacienteRepository, FabricanteRepository
            fabricanteRepository, VacinaRepository vacinaRepository){
        setContentPane(contentPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);


        btRelatorioVacinacoesPorPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RelatorioComFiltroEditor relatorioComFiltroEditor = new RelatorioComFiltroEditor(RELATORIO_VACINACOES_POR_CLIENTE,
                        vacinacaoRepository, pacienteRepository, fabricanteRepository, vacinaRepository);
                relatorioComFiltroEditor.setVisible(true);
            }
        });

        btRelatorioVacinacoesPorVacina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RelatorioComFiltroEditor relatorioComFiltroEditor = new RelatorioComFiltroEditor(RELATORIO_VACINACOES_POR_VACINA,
                        vacinacaoRepository, pacienteRepository, fabricanteRepository, vacinaRepository);
                relatorioComFiltroEditor.setVisible(true);
            }
        });

        btTodasVacinacoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RelatorioComFiltroEditor relatorioComFiltroEditor = new RelatorioComFiltroEditor(RELATORIO_TODAS_VACINACOES,
                        vacinacaoRepository, pacienteRepository, fabricanteRepository, vacinaRepository);
                relatorioComFiltroEditor.setVisible(true);
            }
        });

        btTodosPacientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RelatorioComFiltroEditor relatorioComFiltroEditor = new RelatorioComFiltroEditor(RELATORIO_CADASTRO_PACIENTE,
                        vacinacaoRepository, pacienteRepository, fabricanteRepository, vacinaRepository);
                relatorioComFiltroEditor.setVisible(true);
            }
        });

        btTodasFabricantes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RelatorioComFiltroEditor relatorioComFiltroEditor = new RelatorioComFiltroEditor(RELATORIO_CADASTRO_FABRICANTE,
                        vacinacaoRepository, pacienteRepository, fabricanteRepository, vacinaRepository);
                relatorioComFiltroEditor.setVisible(true);
            }
        });

        btTodasVacinas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RelatorioComFiltroEditor relatorioComFiltroEditor = new RelatorioComFiltroEditor(RELATORIO_CADASTRO_VACINA,
                        vacinacaoRepository, pacienteRepository, fabricanteRepository, vacinaRepository);
                relatorioComFiltroEditor.setVisible(true);
            }
        });


        btVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

}
