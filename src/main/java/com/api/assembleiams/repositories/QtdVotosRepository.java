package com.api.assembleiams.repositories;


import com.api.assembleiams.enums.StatusVoto;
import com.api.assembleiams.models.OpcaoModel;
import com.api.assembleiams.models.QTDVotosModel;
import com.api.assembleiams.models.VotosModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface QtdVotosRepository extends JpaRepository<QTDVotosModel, Integer> {
    QTDVotosModel findByPautaAndOpcaoAndSessaoAndStatusVoto(Integer pauta, Integer opcao, Integer sessao, StatusVoto statusVoto);
}
