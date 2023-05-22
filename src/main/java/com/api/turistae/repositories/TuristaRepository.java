package com.api.turistae.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.turistae.models.Turista;

public interface TuristaRepository extends JpaRepository<Turista, Long> {

    @Query("SELECT t FROM Turista t LEFT JOIN FETCH t.usuario WHERE t.id = :id")
    Optional<Turista> findById(long id);

    @Query("SELECT t FROM Turista t LEFT JOIN FETCH t.usuario")
    List<Turista> findAll();

}
