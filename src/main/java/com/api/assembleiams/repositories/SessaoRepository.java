package com.api.assembleiams.repositories;


import com.api.assembleiams.models.PautaModel;
import com.api.assembleiams.models.SessaoModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SessaoRepository extends JpaRepository<SessaoModel, Integer> {

}
