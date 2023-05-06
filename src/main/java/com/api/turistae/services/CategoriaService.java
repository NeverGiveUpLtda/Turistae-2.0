package com.api.turistae.services;

import java.util.List;

import com.api.turistae.dtos.CategoriaDTO;
import com.api.turistae.dtos.DadosCategoriaDTO;

public interface CategoriaService {

    // Create
    Long post(CategoriaDTO dto);

    // Read
    List<DadosCategoriaDTO> getAll();

    DadosCategoriaDTO getById(Long id);

    // Update
    void put(Long id, CategoriaDTO dto);

    // Delete
    void delete(Long id);

}
