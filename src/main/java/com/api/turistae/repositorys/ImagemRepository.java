package com.api.turistae.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.turistae.models.Imagem;
import com.api.turistae.models.Turismo;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {

    List<Imagem> findAllByTurismo(Turismo turismo);

    @Modifying
    @Query("DELETE FROM Imagem i WHERE i.turismo.id = :turismoId")
    void deleteByTurismoId(@Param("turismoId") Long turismoId);
}
