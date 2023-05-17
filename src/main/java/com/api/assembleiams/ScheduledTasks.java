package com.api.assembleiams;

import com.api.assembleiams.models.OpcaoModel;
import com.api.assembleiams.models.PautaModel;
import com.api.assembleiams.models.SessaoModel;
import com.api.assembleiams.repositories.PautaRepository;
import com.api.assembleiams.repositories.SessaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Validated
@EnableScheduling
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private final long SEGUNDO = 1000;
    private final long MINUTO = SEGUNDO * 60;
    private final long HORA = MINUTO * 60;

    @Autowired
    private SessaoRepository sessaoRepository;
    @Autowired
    private PautaRepository pautaRepository;


    @Scheduled(fixedDelay = 1 * 60 * 1000)  // cada um minuto
    public void encerrarSessao() {

        try {
            encerrarPauta();
            log.info("Pauta ENCERRADA!");

        } catch (Exception e) {

            e.printStackTrace();

            String mensagem = e.getMessage() != null ? e.getMessage() : "Falha na Scheduled ao encerrar PAUTAS";

            log.error(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private void encerrarPauta() {
        List<SessaoModel> listSessao = sessaoRepository.findAll();
        if (!listSessao.isEmpty()) {

            for (SessaoModel sessao : listSessao) {
                if (LocalDateTime.now().isAfter(sessao.getDataHoraFim().toLocalDateTime())) {
                    PautaModel pautaModel = pautaRepository.findPautaAberta(sessao.getPauta());
                    pautaModel.setVotar(false);
                    pautaRepository.save(pautaModel);
                }
            }

        }
    }

}
