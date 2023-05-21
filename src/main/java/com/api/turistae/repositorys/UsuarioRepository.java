package com.api.turistae.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.turistae.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByEmail(String email);

    Usuario findByNome(String nome);

    @Query("SELECT u FROM Usuario u WHERE u.email = :emailOrNome OR u.nome = :emailOrNome")
    Usuario findByEmailOrNome(@Param("emailOrNome") String emailOrNome);

}
