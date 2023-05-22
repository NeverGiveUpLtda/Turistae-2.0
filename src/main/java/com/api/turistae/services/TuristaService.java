package com.api.turistae.services;

import java.util.List;

import com.api.turistae.dtos.DadosTuristaDTO;
import com.api.turistae.dtos.TuristaDTO;

public interface TuristaService {

    // Create
    Long post(TuristaDTO dto);

    // Read
    List<DadosTuristaDTO> getAll();

    DadosTuristaDTO getById(Long id);

    // Update
    void put(TuristaDTO dto);

    // Delete
    void delete(Long id);

}
