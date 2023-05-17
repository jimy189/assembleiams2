package com.api.assembleiams.models;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
@Entity
@Table(name = "TB_OPCAO")
public class OpcaoModel extends
        RepresentationModel<OpcaoModel> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomeOpcao;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sessao", referencedColumnName = "id", updatable = false, insertable = false)
    private SessaoModel sessaoBySessao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pauta", referencedColumnName = "id", updatable = false, insertable = false)
    private PautaModel pautaByPauta;*/

    @Basic
    @Column(name = "pauta", nullable = true)
    private Integer pauta;

    @Basic
    @Column(name = "sessao", nullable = true)
    private Integer sessao;
}
