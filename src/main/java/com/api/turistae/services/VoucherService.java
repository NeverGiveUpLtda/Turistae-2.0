package com.api.turistae.services;

import java.util.List;

import com.api.turistae.dtos.DadosVoucherDTO;
import com.api.turistae.dtos.VoucherDTO;

public interface VoucherService {

    // Create
    Long post(VoucherDTO dto);

    // Read
    List<DadosVoucherDTO> getAll();

    DadosVoucherDTO getById(Long id);

    // Update
    void put(Long id, VoucherDTO dto);

    // Delete
    void delete(Long id);

}
