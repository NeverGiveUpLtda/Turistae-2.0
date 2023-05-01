package com.api.turistae.services;

import java.util.List;

import com.api.turistae.dtos.DadosImagemDTO;
import com.api.turistae.dtos.ImagemDTO;

public interface ImagemService {

    // Create
    Long post(ImagemDTO dto);

    // Read
    List<DadosImagemDTO> getAll();

    DadosImagemDTO getById(Long id);

    // Update
    void put(Long id, ImagemDTO dto);

    // Delete
    void delete(Long id);

}
