package com.api.assembleiams.repositories;


import com.api.assembleiams.enums.StatusVoto;
import com.api.assembleiams.models.QTDVotosModel;
import com.api.assembleiams.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
    Optional<UsuarioModel> findByCpf(String cpf);

}
