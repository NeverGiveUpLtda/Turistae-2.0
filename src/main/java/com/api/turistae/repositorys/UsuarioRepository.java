package com.api.turistae.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.turistae.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE (u.nomeUsuario = :nomeUsuario OR u.email = :email) AND u.senha = :senha")
    Optional<Usuario> login(@Param("nomeUsuario") String nomeUsuario, @Param("email") String email, @Param("senha") String password);

}
