package com.br.gestao_vacinacao.repositories;

import com.br.gestao_vacinacao.model.entities.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacinaRepository extends JpaRepository<Vacina, Long> {

    Vacina findByNome(String nome);
}
