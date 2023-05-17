package com.api.assembleiams.repositories;


import com.api.assembleiams.enums.StatusVoto;
import com.api.assembleiams.models.PautaModel;
import com.api.assembleiams.models.QTDVotosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PautaRepository extends JpaRepository<PautaModel, Integer> {



    @Query("select p from PautaModel p  where p.id = :idPauta")
    PautaModel findPautaAberta(Integer idPauta);

    @Query("select p from PautaModel p  where p.id = :idPauta and p.votar = :votar")
    PautaModel findPautaFechada(Integer idPauta, Boolean votar);

}
