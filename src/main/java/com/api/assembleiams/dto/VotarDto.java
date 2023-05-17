package com.api.assembleiams.dto;

import com.api.assembleiams.enums.StatusVoto;
import lombok.Data;

@Data
public class VotarDto {

    private String nomeOpcao;
    private Integer sessao;
    private Integer pauta;
    private Integer opcao;
    private String cpf;
    private StatusVoto statusVoto;
    //  private cpf
    //  private idUsuario
}
