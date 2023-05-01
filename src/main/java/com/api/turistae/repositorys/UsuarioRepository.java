package com.api.turistae.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.turistae.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
