package com.br.gestao_vacinacao.repositorios;

import com.br.gestao_vacinacao.entidades.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Paciente findByCpf(String cpf);

}
