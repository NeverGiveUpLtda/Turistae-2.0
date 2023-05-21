package com.api.turistae.services;

import java.util.List;

import com.api.turistae.dtos.CurtidaDTO;
import com.api.turistae.dtos.DadosCurtidaDTO;

public interface CurtidaService {

    // Create
    Long post(CurtidaDTO dto);

    // Read
    List<DadosCurtidaDTO> getAll();

    List<DadosCurtidaDTO> getByTurismo(Long turismoId);

    DadosCurtidaDTO getById(Long id);

    DadosCurtidaDTO getCurtidaByTurismoAndTurista(Long turistaId, Long turismoId);

    // Update
    void put(Long id, CurtidaDTO dto);

    // Delete
    void delete(Long id);

}
