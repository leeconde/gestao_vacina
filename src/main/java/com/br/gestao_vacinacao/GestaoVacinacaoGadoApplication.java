package com.br.gestao_vacinacao;

import com.br.gestao_vacinacao.repositorios.FabricanteRepository;
import com.br.gestao_vacinacao.repositorios.PacienteRepository;
import com.br.gestao_vacinacao.repositorios.VacinaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class GestaoVacinacaoGadoApplication {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        ConfigurableApplicationContext context = SpringApplication.run(GestaoVacinacaoGadoApplication.class, args);

        PacienteRepository pacienteRepository = context.getBean(PacienteRepository.class);
        FabricanteRepository fabricanteRepository = context.getBean(FabricanteRepository.class);
        VacinaRepository vacinaRepository = context.getBean(VacinaRepository.class);
//		PacienteRepository pacienteRepository = context.getBean(PacienteRepository.class);
//		PacienteRepository pacienteRepository = context.getBean(PacienteRepository.class);

        SwingUtilities.invokeLater(() -> new TelaMenu(pacienteRepository, fabricanteRepository, vacinaRepository).setVisible(true));
    }

}
