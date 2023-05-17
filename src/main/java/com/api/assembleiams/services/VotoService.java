package com.api.assembleiams.services;

import com.api.assembleiams.dto.VotarDto;
import com.api.assembleiams.models.OpcaoModel;
import com.api.assembleiams.models.PautaModel;
import com.api.assembleiams.models.QTDVotosModel;
import com.api.assembleiams.models.UsuarioModel;
import com.api.assembleiams.models.VotosModel;
import com.api.assembleiams.repositories.OpcaoRepository;
import com.api.assembleiams.repositories.PautaRepository;
import com.api.assembleiams.repositories.QtdVotosRepository;
import com.api.assembleiams.repositories.UsuarioRepository;
import com.api.assembleiams.repositories.VotosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VotoService {
    final OpcaoRepository opcaoRepository;
    final VotosRepository votosRepository;
    final UsuarioRepository usuarioRepository;
    final PautaRepository pautaRepository;
    final QtdVotosRepository qtdVotosRepository;

    public VotoService(OpcaoRepository opcaoRepository,VotosRepository votosRepository, QtdVotosRepository qtdVotosRepository, PautaRepository pautaRepository, UsuarioRepository usuarioRepository) {
        this.opcaoRepository = opcaoRepository;
        this.qtdVotosRepository = qtdVotosRepository;
        this.votosRepository = votosRepository;
        this.pautaRepository = pautaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public VotosModel save(VotarDto votarDto) {
        var votosModel = new VotosModel();
        atualizarQtdVotos(votarDto);
        votosModel.setStatusVoto(votarDto.getStatusVoto());
        votosModel.setSessao(votarDto.getSessao());
        votosModel.setPauta(votarDto.getPauta());
        votosModel.setOpcao(votarDto.getOpcao());
        votosModel.setCpf(votarDto.getCpf());
        return votosRepository.save(votosModel);
    }

    public UsuarioModel saveUsuario(UsuarioModel usuarioModel) {
        return usuarioRepository.save(usuarioModel);
    }
    public PautaModel pautaFechada(Integer idPauta)  {
        return pautaRepository.findPautaFechada(idPauta, false);

    }

    public void atualizarQtdVotos(VotarDto votarDto) {
        QTDVotosModel qtdVotosModel = qtdVotosRepository.findByPautaAndOpcaoAndSessaoAndStatusVoto
                    (votarDto.getPauta(), votarDto.getOpcao(),
                            votarDto.getSessao(), votarDto.getStatusVoto());
        if (qtdVotosModel != null) {
            qtdVotosModel.setQtdVoto(qtdVotosModel.getQtdVoto() +1);
            qtdVotosRepository.save(qtdVotosModel);
        }else{
            var qtdVotosNova = new QTDVotosModel();
            qtdVotosNova.setQtdVoto(1);
            qtdVotosNova.setStatusVoto(votarDto.getStatusVoto());
            qtdVotosNova.setSessao(votarDto.getSessao());
            qtdVotosNova.setPauta(votarDto.getPauta());
            qtdVotosNova.setOpcao(votarDto.getOpcao());
            qtdVotosRepository.save(qtdVotosNova);
        }
    }

    public List<VotosModel> findAllQtdVotos() {
        return votosRepository.findAll();
    }

    public List<QTDVotosModel> findAllVotos() {
        return qtdVotosRepository.findAll();
    }

    public Optional<OpcaoModel> findById(Integer id) {
        return opcaoRepository.findById(id);
    }

    public Optional<UsuarioModel> findByIdUsuario(String cpf) {
        return usuarioRepository.findByCpf(cpf);
    }


}
