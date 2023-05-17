package com.api.assembleiams.services;

import com.api.assembleiams.dto.OpcaoDto;
import com.api.assembleiams.dto.PautaDto;
import com.api.assembleiams.models.OpcaoModel;
import com.api.assembleiams.models.PautaModel;
import com.api.assembleiams.models.SessaoModel;
import com.api.assembleiams.repositories.OpcaoRepository;
import com.api.assembleiams.repositories.SessaoRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class OpcaoService {

    final OpcaoRepository opcaoRepository;

    public OpcaoService(OpcaoRepository opcaoRepository) {
        this.opcaoRepository = opcaoRepository;
    }

    public void save(List<OpcaoDto> listapautaDto,Integer idSessao, Integer idPauta) {

        for (OpcaoDto opcao : listapautaDto) {
            var opcaoModel = new OpcaoModel();
            opcaoModel.setNomeOpcao(opcao.getNomeOpcao());
            opcaoModel.setSessao(idSessao);
            opcaoModel.setPauta(idPauta);
            opcaoRepository.save(opcaoModel);
        }

    }

    public List<OpcaoModel> findAll() {
        return opcaoRepository.findAll();
    }

    public Optional<OpcaoModel> findById(Integer id) {
        return opcaoRepository.findById(id);
    }

    public List<OpcaoModel> findAllByIdPautaByIdSessao(Integer idPauta,
                                                       Integer idSessao) {
        return opcaoRepository.findByPautaAndSessao(idPauta, idSessao);
    }
}
