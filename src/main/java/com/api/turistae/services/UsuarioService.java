package com.api.turistae.services;

import java.util.List;

import com.api.turistae.dtos.DadosUsuarioDTO;
import com.api.turistae.dtos.PutUsuarioDTO;
import com.api.turistae.dtos.UsuarioDTO;

public interface UsuarioService {

    // Create
    Long post(UsuarioDTO dto);

    // Read
    List<DadosUsuarioDTO> getAll();

    DadosUsuarioDTO getById(Long id);

    // Update
    void put(Long id, PutUsuarioDTO dto);

    // Delete
    void delete(Long id);

    // Métodos
    DadosUsuarioDTO login(String usuario, String senha);

}
