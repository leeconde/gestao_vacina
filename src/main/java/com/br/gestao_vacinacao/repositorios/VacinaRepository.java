package com.br.gestao_vacinacao.repositorios;

import com.br.gestao_vacinacao.entidades.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacinaRepository extends JpaRepository<Vacina, Long> {
}
