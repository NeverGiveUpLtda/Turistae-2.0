package com.api.turistae.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.turistae.models.Review;
import com.api.turistae.models.Turismo;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByTurismo(Turismo turismo);

    @Modifying
    @Query("DELETE FROM Review r WHERE r.usuario.id = :usuarioId")
    void deleteByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Modifying
    @Query("DELETE FROM Review r WHERE r.turismo.id = :turismoId")
    void deleteByTurismoId(@Param("turismoId") Long turismoId);
}
