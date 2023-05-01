package com.api.turistae.services;

import java.util.List;

import com.api.turistae.dtos.CategoriaDto;
import com.api.turistae.dtos.DadosCategoriaDTO;

public interface CategoriaService {
    
    // Create
    Long post(CategoriaDto dto);

    // Read
    List<DadosCategoriaDTO> getAll();

    DadosCategoriaDTO getById(Long id);

    // Update
    void put(Long id, CategoriaDto dto);

    // Delete
    void delete(Long id);

}
