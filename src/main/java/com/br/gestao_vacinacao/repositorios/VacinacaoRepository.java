package com.br.gestao_vacinacao.repositorios;

import com.br.gestao_vacinacao.entidades.Paciente;
import com.br.gestao_vacinacao.entidades.Vacina;
import com.br.gestao_vacinacao.entidades.Vacinacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacinacaoRepository extends JpaRepository<Vacinacao, Long> {

    List<Vacinacao> findAllByPaciente(Paciente paciente);

    List<Vacinacao> findAllByVacina(Vacina vacina);
}
