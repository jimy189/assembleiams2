package com.api.assembleiams.controllers;

import com.api.assembleiams.dto.PautaDto;
import com.api.assembleiams.dto.UsuarioDto;
import com.api.assembleiams.dto.VotarDto;
import com.api.assembleiams.enums.StatusVoto;
import com.api.assembleiams.models.OpcaoModel;
import com.api.assembleiams.models.PautaModel;
import com.api.assembleiams.models.UsuarioModel;
import com.api.assembleiams.models.VotosModel;
import com.api.assembleiams.services.OpcaoService;
import com.api.assembleiams.services.PautaService;
import com.api.assembleiams.services.VotoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pauta-ms")
public class PautaController {

    final PautaService pautaService;
    final OpcaoService opcaoService;
    final VotoService votoService;

    public PautaController(PautaService pautaService, OpcaoService opcaoService, VotoService votoService) {
        this.pautaService = pautaService;
        this.opcaoService = opcaoService;
        this.votoService = votoService;
    }
    @PostMapping
    public ResponseEntity<Object> savePauta(@RequestBody @Valid PautaDto pautaDto){
        var pautaModel = new PautaModel();
        pautaDto.setDataRegisto(new Timestamp(System.currentTimeMillis()));
        BeanUtils.copyProperties(pautaDto, pautaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(pautaService.save(pautaModel,pautaDto.getListaOpcao()));
    }
    @PostMapping("/salvarvoto/{idpauta}/{idsessao}/{idopcao}/{nomeopcao}/{cpf}/{statusVoto}")
    public ResponseEntity<Object> saveVoto(@PathVariable(value = "idpauta") Integer idPauta,
                                           @PathVariable(value = "idsessao") Integer idSessao,
                                           @PathVariable(value = "idopcao") Integer idOpcao,
                                           @PathVariable(value = "nomeopcao") String nomeOpcao,
                                           @PathVariable(value = "cpf") String cpf,
                                           @PathVariable(value = "statusVoto") StatusVoto statusVoto){
        PautaModel pautaFechada = votoService.pautaFechada(idPauta);
        if (pautaFechada != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Esta Pauta não ser votada pelo tempo expirado.");
        }
        var votosModel = new VotosModel();
        VotarDto votarDto = new VotarDto();
        votarDto.setPauta(idPauta);
        votarDto.setOpcao(idOpcao);
        votarDto.setSessao(idSessao);
        votarDto.setNomeOpcao(nomeOpcao);
        votarDto.setCpf(cpf);
        votarDto.setStatusVoto(statusVoto);
        BeanUtils.copyProperties(votarDto, votosModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(votoService.save(votarDto));
    }

    @PostMapping("/salvarusuario")
    public ResponseEntity<Object> saveUsuario(@RequestBody @Valid UsuarioDto usuarioDto){
        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, usuarioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(votoService.saveUsuario(usuarioModel));
    }

    @GetMapping("/lista")
    public ResponseEntity<List<PautaModel>> getAllPauta(){
        List<PautaModel> pautaList =
                pautaService.findAll();
        if(pautaList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<PautaModel>>(pautaList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PautaModel> getOnePauta(@PathVariable(value = "id") Integer id){

        Optional<PautaModel> parkingSpotModelOptional = pautaService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<PautaModel>(parkingSpotModelOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UsuarioModel> getOneUsuario(@PathVariable(value = "id") String cpf){
        Optional<UsuarioModel> usuario = votoService.findByIdUsuario(cpf);
        if (!usuario.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UsuarioModel>(usuario.get(), HttpStatus.OK);
    }

    @GetMapping("/listaopcoes/{idpauta}/{idsessao}")
    public ResponseEntity<List<OpcaoModel>> getAllOpcoes(@PathVariable(value = "idpauta") Integer idPauta,
                                                         @PathVariable(value = "idsessao") Integer idSessao) {
        List<OpcaoModel> opcaoList = opcaoService.findAllByIdPautaByIdSessao(idPauta, idSessao);
        if (opcaoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (OpcaoModel opcao : opcaoList) {
                Integer id = opcao.getId();
                VotarDto votarDto = new VotarDto();
                votarDto.setPauta(opcao.getPauta());
                votarDto.setOpcao(opcao.getId());
                votarDto.setSessao(opcao.getSessao());
                opcao.add(linkTo(methodOn(PautaController.class).saveVoto(opcao.getPauta(), opcao.getSessao(), opcao.getId(), opcao.getNomeOpcao(),"03988551538", StatusVoto.SIM)).withSelfRel());
            }
            return new ResponseEntity<List<OpcaoModel>>(opcaoList, HttpStatus.OK);
        }
    }
}
