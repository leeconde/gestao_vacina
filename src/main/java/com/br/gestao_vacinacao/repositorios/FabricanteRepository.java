package com.br.gestao_vacinacao.repositorios;

import com.br.gestao_vacinacao.entidades.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FabricanteRepository extends JpaRepository<Fabricante, Long> {

    Fabricante findByCnpj(String cnpj);

}
