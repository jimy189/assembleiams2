package com.api.assembleiams.models;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "pergunta_recorrencia_dash")
public class PerguntaRecorrenciaDashBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "recorrencia_id")
    private Long idRecorrencia;

    @Column(name = "campo_id")
    private Long idPergunta;

    @Column(name = "numero_recorrencia")
    private Integer numeroRecorrência;

    @Column(name = "data_hora")
    private Timestamp dataHora;

    @Column(name = "rede_id")
    private Long redeId;

    @Column(name = "tipo_campo_id")
    private Long tipoCampoId;

}

