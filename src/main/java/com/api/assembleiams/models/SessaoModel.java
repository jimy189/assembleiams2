package com.api.assembleiams.models;

import jakarta.persistence.Basic;
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
@Table(name = "TB_SESSAO")
public class SessaoModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pauta", referencedColumnName = "id", updatable = false, insertable = false)
    private PautaModel pautaByPauta;
*/
    private Timestamp dataHoraInicio;
    private Timestamp dataHoraFim;
    @Basic
    @Column(name = "pauta", nullable = true)
    private Integer pauta;


}
