package com.br.gestao_vacinacao;

import com.br.gestao_vacinacao.repositories.FabricanteRepository;
import com.br.gestao_vacinacao.repositories.PacienteRepository;
import com.br.gestao_vacinacao.repositories.VacinaRepository;
import com.br.gestao_vacinacao.repositories.VacinacaoRepository;
import com.br.gestao_vacinacao.views.TelaInicial;
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
        VacinacaoRepository vacinacaoRepository = context.getBean(VacinacaoRepository.class);

        SwingUtilities.invokeLater(() -> {
            TelaInicial tela = new TelaInicial(pacienteRepository, fabricanteRepository, vacinaRepository, vacinacaoRepository);
            tela.setVisible(true);
        });
    }

}
