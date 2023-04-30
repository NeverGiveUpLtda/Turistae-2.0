package com.api.turistae.services;

import java.util.List;

import com.api.turistae.dtos.DadosUsuarioDTO;
import com.api.turistae.dtos.UsuarioDTO;

public interface UsuarioService {

    // Create
    Long post(UsuarioDTO dto);

    // Read
    List<DadosUsuarioDTO> getAll();

    DadosUsuarioDTO getById(Long id);

    // Update
    void put(Long id, UsuarioDTO dto);

    // Delete
    void delete(Long id);

}
