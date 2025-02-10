package com.br.gestao_vacinacao.repositories;

import com.br.gestao_vacinacao.model.entities.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FabricanteRepository extends JpaRepository<Fabricante, Long> {

    Fabricante findByCnpj(String cnpj);

}
