package com.api.assembleiams.models;

import com.api.assembleiams.enums.StatusVoto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "TB_QTDVOTOS")
public class QTDVotosModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//  private cpf
//  private idUsuario
    private StatusVoto statusVoto;
    private Integer pauta;
    private Integer opcao;
    private Integer sessao;
    private Integer qtdVoto;
}
