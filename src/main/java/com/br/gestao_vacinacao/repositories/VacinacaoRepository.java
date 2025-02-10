package com.br.gestao_vacinacao.repositories;

import com.br.gestao_vacinacao.model.entities.Paciente;
import com.br.gestao_vacinacao.model.entities.Vacina;
import com.br.gestao_vacinacao.model.entities.Vacinacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacinacaoRepository extends JpaRepository<Vacinacao, Long> {

    List<Vacinacao> findAllByPaciente(Paciente paciente);

    List<Vacinacao> findAllByVacina(Vacina vacina);
}
