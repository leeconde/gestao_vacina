package com.br.gestao_vacinacao.entidades;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class RelatorioVacinacoes {

    private String nomePaciente;

    private String telefonePaciente;

    private String emailPaciente;

    private String nomeVacina;

    private Integer doseVacina;

    private Date dataAplicacaoVacina;

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getTelefonePaciente() {
        return telefonePaciente;
    }

    public void setTelefonePaciente(String telefonePaciente) {
        this.telefonePaciente = telefonePaciente;
    }

    public String getEmailPaciente() {
        return emailPaciente;
    }

    public void setEmailPaciente(String emailPaciente) {
        this.emailPaciente = emailPaciente;
    }

    public String getNomeVacina() {
        return nomeVacina;
    }

    public void setNomeVacina(String nomeVacina) {
        this.nomeVacina = nomeVacina;
    }

    public Integer getDoseVacina() {
        return doseVacina;
    }

    public void setDoseVacina(Integer doseVacina) {
        this.doseVacina = doseVacina;
    }

    public Date getDataAplicacaoVacina() {
        return dataAplicacaoVacina;
    }

    public void setDataAplicacaoVacina(Date dataAplicacaoVacina) {
        this.dataAplicacaoVacina = dataAplicacaoVacina;
    }
}
