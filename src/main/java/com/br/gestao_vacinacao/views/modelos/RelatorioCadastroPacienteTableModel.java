package com.br.gestao_vacinacao.views.modelos;

import com.br.gestao_vacinacao.model.entities.Paciente;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class RelatorioCadastroPacienteTableModel extends AbstractTableModel {
    private final String[] colunas = {"ID", "Nome", "CPF", "Telefone", "Endereco", "Email"};
    private List<Paciente> dados;

    public RelatorioCadastroPacienteTableModel(List<Paciente> dados) {
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
        Paciente paciente = dados.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> paciente.getId();
            case 1 -> paciente.getNome();
            case 2 -> paciente.getCpf();
            case 3 -> paciente.getTelefone();
            case 4 -> paciente.getEndereco();
            case 5 -> paciente.getEmail();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    public void setDados(List<Paciente> novosDados) {
        this.dados = novosDados;
        fireTableDataChanged();
    }
}
