package com.api.turistae.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.api.turistae.dtos.TuristaDTO;
import com.api.turistae.dtos.UsuarioDTO;
import com.api.turistae.models.Usuario;

public interface UsuarioService {

    // Create
    Integer post(UsuarioDTO dto);

    // Read
    List<UsuarioDTO> getAll();

    UsuarioDTO getById(Integer id);

    // Update
    void put(TuristaDTO dto);

    // Delete
    void delete(Integer id);

    // Métodos
    UserDetails auth(Usuario usuario);

}
