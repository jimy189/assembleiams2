package com.api.assembleiams.repositories;


import com.api.assembleiams.models.SessaoModel;
import com.api.assembleiams.models.VotosModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VotosRepository extends JpaRepository<VotosModel, Integer> {

}
