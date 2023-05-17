package com.api.assembleiams.repositories;


import com.api.assembleiams.models.OpcaoModel;
import com.api.assembleiams.models.PerguntaRecorrenciaDashBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PerguntaRecorrenciaDashRepository extends JpaRepository<PerguntaRecorrenciaDashBoard, Integer> {
    List<OpcaoModel> findByPautaAndSessao(Integer pauta, Integer sessao);

    List<PerguntaRecorrenciaDashBoard> findByIdPerguntaAndTipoCampoIdAndRedeId(Long idPergunta, Long tipoCampoId, Long redeId);
}
