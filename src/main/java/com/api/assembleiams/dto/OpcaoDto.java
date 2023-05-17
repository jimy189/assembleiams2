package com.api.assembleiams.dto;

import com.api.assembleiams.enums.StatusVoto;
import lombok.Data;

@Data
public class OpcaoDto {

    private String nomeOpcao;
    private Integer idSessao;
    private Integer idPauta;
}
