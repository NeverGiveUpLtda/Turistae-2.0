package com.api.turistae.services;

import java.util.List;

import com.api.turistae.dtos.DadosTurismoDTO;
import com.api.turistae.dtos.TurismoDTO;

public interface TurismoService {

    // Create
    Long post(TurismoDTO dto);

    // Read
    List<DadosTurismoDTO> getAll();

    List<DadosTurismoDTO> getAllOrderByCurtidas();

    List<DadosTurismoDTO> getAllByCategoria(Long id);

    List<DadosTurismoDTO> getAllByUsuario(Long id);

    List<DadosTurismoDTO> getAllOrderByNota();

    Double calcularMediaNotasPorId(Long turismoId);

    DadosTurismoDTO getById(Long id);

    // Update
    void put(Long id, TurismoDTO dto);

    // Delete
    void delete(Long id);

}
