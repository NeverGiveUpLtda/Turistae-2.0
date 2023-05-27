package com.api.turistae.services;

import java.util.List;

import com.api.turistae.dtos.DadosImagemDTO;
import com.api.turistae.dtos.ImagemDTO;

public interface ImagemService {

    // Create
    Long post(ImagemDTO dto);

    List<Long> postImagens(Long turismoId, List<ImagemDTO> dto);

    // Read
    List<DadosImagemDTO> getAll();

    List<DadosImagemDTO> getByTurismo(Long turismoId);

    DadosImagemDTO getById(Long id);

    // Update
    void put(Long id, ImagemDTO dto);

    // Delete
    void delete(Long id);

}
