package com.br.gestao_vacinacao.services;

import com.br.gestao_vacinacao.model.entities.Fabricante;
import com.br.gestao_vacinacao.model.entities.Vacina;
import com.br.gestao_vacinacao.repositories.FabricanteRepository;
import com.br.gestao_vacinacao.repositories.VacinaRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class VacinaService {
    private final VacinaRepository vacinaRepository;
    private final FabricanteRepository fabricanteRepository;

    public VacinaService(VacinaRepository vacinaRepository, FabricanteRepository fabricanteRepository) {
        this.vacinaRepository = vacinaRepository;
        this.fabricanteRepository = fabricanteRepository;
    }

    public Fabricante buscarFabricantePorCnpj(String cnpj) {
        if (cnpj == null || cnpj.trim().isEmpty()) {
            throw new IllegalArgumentException("O CNPJ não pode ser vazio.");
        }

        return Optional.ofNullable(fabricanteRepository.findByCnpj(cnpj))
                .orElseThrow(() -> new IllegalArgumentException("Fabricante não encontrado com esse CNPJ."));
    }

    public void salvarVacina(String nomeVacina, String dataValidadeStr, Fabricante fabricante) {
        if (nomeVacina == null || nomeVacina.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da vacina não pode estar vazio.");
        }

        if (fabricante == null) {
            throw new IllegalArgumentException("É necessário informar um fabricante.");
        }

        Date dataValidade = validarData(dataValidadeStr);

        Vacina vacina = new Vacina();
        vacina.setNome(nomeVacina);
        vacina.setDataValidade(dataValidade);
        vacina.setFabricante(fabricante);

        vacinaRepository.save(vacina);
    }

    private Date validarData(String dataStr) {
        if (dataStr == null || dataStr.trim().isEmpty() || dataStr.contains("_")) {
            throw new IllegalArgumentException("Preencha a data corretamente! O formato válido é dd/MM/yyyy.");
        }

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);
        try {
            return format.parse(dataStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("O formato da data está inválido.");
        }
    }
}
