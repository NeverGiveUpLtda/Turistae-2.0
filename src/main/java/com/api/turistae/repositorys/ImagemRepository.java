package com.api.turistae.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.turistae.models.Imagem;
import com.api.turistae.models.Turismo;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {

    List<Imagem> findAllByTurismo(Turismo turismo);

}
