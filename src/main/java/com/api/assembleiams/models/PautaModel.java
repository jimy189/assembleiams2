package com.api.assembleiams.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "TB_PAUTA")
public class PautaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false, updatable=false)
    private Integer id;
    private String nomePauta;
    private Integer VotosTotais;
    private Integer duracaoSessao;
    private Timestamp dataRegisto;
    private Boolean votar;
}
