package com.br.gestao_vacinacao.modelos;

import com.br.gestao_vacinacao.entidades.Fabricante;
import com.br.gestao_vacinacao.entidades.Paciente;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class RelatorioCadastroFabricanteTableModel extends AbstractTableModel {
    private final String[] colunas = {"ID", "Nome", "CNPJ"};
    private List<Fabricante> dados;

    public RelatorioCadastroFabricanteTableModel(List<Fabricante> dados) {
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
        Fabricante fabricante = dados.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> fabricante.getId();
            case 1 -> fabricante.getNome();
            case 2 -> fabricante.getCnpj();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    public void setDados(List<Fabricante> novosDados) {
        this.dados = novosDados;
        fireTableDataChanged();
    }
}
