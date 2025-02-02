package com.br.gestao_vacinacao.modelos;

import com.br.gestao_vacinacao.entidades.RelatorioVacinacoes;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class RelatorioVacinacoesTableModel extends AbstractTableModel {
    private final String[] colunas = {"Nome paciente", "Telefone", "Email", "Vacina", "Dose", "Data Aplicação"};
    private List<RelatorioVacinacoes> dados;

    public RelatorioVacinacoesTableModel(List<RelatorioVacinacoes> dados) {
        this.dados = dados;
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RelatorioVacinacoes relatorio = dados.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> relatorio.getNomePaciente();
            case 1 -> relatorio.getTelefonePaciente();
            case 2 -> relatorio.getEmailPaciente();
            case 3 -> relatorio.getNomeVacina();
            case 4 -> relatorio.getDoseVacina();
            case 5 -> relatorio.getDataAplicacaoVacina();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    public void setDados(List<RelatorioVacinacoes> novosDados) {
        this.dados = novosDados;
        fireTableDataChanged();
    }
}
