package com.api.turistae.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.turistae.models.Curtida;
import com.api.turistae.models.Turismo;
import com.api.turistae.models.Turista;

public interface CurtidaRepository extends JpaRepository<Curtida, Long> {

    List<Curtida> findAllByTurismo(Turismo turismo);

    Optional<Curtida> findByTuristaAndTurismo(Turista turista, Turismo turismo);

}
