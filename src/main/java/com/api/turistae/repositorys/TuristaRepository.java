package com.api.turistae.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.turistae.models.Turista;

public interface TuristaRepository extends JpaRepository<Turista, Long> {

    // TODO
    // @Query("SELECT u FROM Usuario u WHERE (u.nomeUsuario = :nomeUsuario OR
    // u.email = :email) AND u.senha = :senha")
    // Optional<Turista> login(@Param("nomeUsuario") String nomeUsuario,
    // @Param("email") String email, @Param("senha") String password);

}
