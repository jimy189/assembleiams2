package com.api.assembleiams.repositories;


import com.api.assembleiams.models.OpcaoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OpcaoRepository extends JpaRepository<OpcaoModel, Integer> {
    List<OpcaoModel> findByPautaAndSessao(Integer pauta, Integer sessao);
}
