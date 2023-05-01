package com.api.turistae.services;

import java.util.List;

import com.api.turistae.dtos.DadosReviewDTO;
import com.api.turistae.dtos.ReviewDTO;

public interface ReviewService {

    // Create
    Long post(ReviewDTO dto);

    // Read
    List<DadosReviewDTO> getAll();

    DadosReviewDTO getById(Long id);

    // Update
    void put(Long id, ReviewDTO dto);

    // Delete
    void delete(Long id);

}
