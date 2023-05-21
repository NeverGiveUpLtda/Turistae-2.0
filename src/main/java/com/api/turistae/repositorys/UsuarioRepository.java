package com.api.turistae.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.turistae.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE u.email = :email OR u.nome = :nome AND u.senha = :senha")
    Usuario findByEmailOrNomeAndSenha(@Param("email") String email, @Param("nome") String nome, @Param("senha") String senha);

}
