package com.br.gestao_vacinacao.repositories;

import com.br.gestao_vacinacao.model.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Paciente findByCpf(String cpf);

}
