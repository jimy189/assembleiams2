package com.api.assembleiams.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class PautaDto {
    private Integer id;
    private String nomePauta;
    private Integer votosTotais;
    private Integer duracaoSessao;
    private Timestamp dataRegisto;
    private List<OpcaoDto> listaOpcao;
}
