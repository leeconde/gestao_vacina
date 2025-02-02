package com.br.gestao_vacinacao.modelos;

import com.br.gestao_vacinacao.entidades.RelatorioVacinas;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class RelatorioCadastroVacinaTableModel extends AbstractTableModel {
    private final String[] colunas = {"ID", "Nome", "Validade", "Nome fabricante"};
    private List<RelatorioVacinas> dados;

    public RelatorioCadastroVacinaTableModel(List<RelatorioVacinas> dados) {
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
        RelatorioVacinas relatorio = dados.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> relatorio.getId();
            case 1 -> relatorio.getNome();
            case 2 -> relatorio.getValidade();
            case 3 -> relatorio.getNomeFabricante();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    public void setDados(List<RelatorioVacinas> novosDados) {
        this.dados = novosDados;
        fireTableDataChanged();
    }
}
