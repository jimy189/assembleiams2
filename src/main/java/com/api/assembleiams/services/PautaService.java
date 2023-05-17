package com.api.assembleiams.services;

import com.api.assembleiams.dto.OpcaoDto;
import com.api.assembleiams.models.PautaModel;
import com.api.assembleiams.models.SessaoModel;
import com.api.assembleiams.repositories.PautaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PautaService {
    final PautaRepository pautaRepository;
    final  OpcaoService opcaoService;
    final  SessaoService sessaoService;

    public PautaService(PautaRepository pautaRepository, SessaoService sessaoService, OpcaoService opcaoService) {
        this.pautaRepository = pautaRepository;
        this.sessaoService = sessaoService;
        this.opcaoService = opcaoService;
    }
    public PautaModel save(PautaModel pautaModel, List<OpcaoDto> listaOpcao) {
        pautaModel.setVotar(true);
        PautaModel pauta = pautaRepository.save(pautaModel);
        SessaoModel sessao = sessaoService.save(pautaModel);
        opcaoService.save(listaOpcao, sessao.getId(), pauta.getId());
        return pautaModel;
    }
    public List<PautaModel> findAll() {
        return pautaRepository.findAll();
    }

    public Optional<PautaModel> findById(Integer id) {
        return pautaRepository.findById(id);
    }

}
