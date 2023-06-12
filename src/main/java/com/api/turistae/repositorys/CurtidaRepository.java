package com.api.turistae.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.turistae.models.Curtida;
import com.api.turistae.models.Turismo;
import com.api.turistae.models.Usuario;

public interface CurtidaRepository extends JpaRepository<Curtida, Long> {

    List<Curtida> findAllByTurismo(Turismo turismo);

    Optional<Curtida> findByUsuarioAndTurismo(Usuario usuario, Turismo turismo);

    @Modifying
    @Query("DELETE FROM Curtida c WHERE c.usuario.id = :usuarioId")
    void deleteByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Modifying
    @Query("DELETE FROM Curtida c WHERE c.turismo.id = :turismoId")
    void deleteByTurismoId(@Param("turismoId") Long turismoId);

}
