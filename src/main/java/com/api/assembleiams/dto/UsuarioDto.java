package com.api.assembleiams.dto;

import com.api.assembleiams.enums.StatusCPF;
import lombok.Data;

@Data
public class UsuarioDto {

    private Integer id;
    private String nome;
    private String cpf;
    private StatusCPF statusCPF;
}
