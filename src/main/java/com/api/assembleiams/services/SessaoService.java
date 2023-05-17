package com.api.assembleiams.services;

import com.api.assembleiams.models.PautaModel;
import com.api.assembleiams.models.SessaoModel;
import com.api.assembleiams.repositories.SessaoRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class SessaoService {

    final SessaoRepository sessaoRepository;

    public SessaoService(SessaoRepository sessaoRepository) {
        this.sessaoRepository = sessaoRepository;
    }

    public SessaoModel save(PautaModel pautaModel) {
        var sessaoModelModel = new SessaoModel();
       // sessaoModelModel.setPautaByPauta(pautaModel);
        sessaoModelModel.setDataHoraInicio(pautaModel.getDataRegisto());
        long valor = pautaModel.getDataRegisto().getTime() + ((pautaModel.getDuracaoSessao() * 60) * 1000);
        sessaoModelModel.setDataHoraFim(new Timestamp(valor));
        sessaoModelModel.setPauta(pautaModel.getId());
        return sessaoRepository.save(sessaoModelModel);
    }

    public List<SessaoModel> findAll() {
        return sessaoRepository.findAll();
    }

    public Optional<SessaoModel> findById(Integer id) {
        return sessaoRepository.findById(id);
    }

}
