package com.br.gestao_vacinacao.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "vacina")
@NoArgsConstructor
@AllArgsConstructor
public class Vacina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    private String nome;

    @Column(name = "data_validade")
    private Date dataValidade;

    @ManyToOne
    @JoinColumn(name = "fabricante_id")
    private Fabricante fabricante;
}
