package com.api.turistae.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.turistae.models.Curtida;
import com.api.turistae.models.Turismo;

public interface CurtidaRepository extends JpaRepository<Curtida, Long> {

    List<Curtida> findAllByTurismo(Turismo turismo);

}
