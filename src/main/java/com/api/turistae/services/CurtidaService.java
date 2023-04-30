package com.api.turistae.services;

import java.util.List;

import com.api.turistae.dtos.CurtidaDTO;
import com.api.turistae.dtos.DadosCurtidaDTO;

public interface CurtidaService {

    // Create
    Long post(CurtidaDTO dto);

    // Read
    List<DadosCurtidaDTO> getAll();

    DadosCurtidaDTO getById(Long id);

    // Update
    void put(Long id, CurtidaDTO dto);

    // Delete
    void delete(Long id);
}
