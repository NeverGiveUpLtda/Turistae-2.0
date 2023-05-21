package com.api.turistae.services;

import java.util.List;

import com.api.turistae.dtos.DadosVoucherDTO;
import com.api.turistae.dtos.VoucherDTO;

public interface VoucherService {

    // Create
    Long post(VoucherDTO dto);

    // Read
    List<DadosVoucherDTO> getAll();

    List<DadosVoucherDTO> getVouchersSemTurista(Long turismoId);

    List<DadosVoucherDTO> getVouchersComTurista(Long turismoId);

    List<DadosVoucherDTO> getVouchersPorTurismo(Long turismoId);

    List<DadosVoucherDTO> getVouchersDoTurista(Long turistaId);

    DadosVoucherDTO getById(Long id);

    DadosVoucherDTO claim(VoucherDTO dto);

    // Update
    void put(Long id, VoucherDTO dto);

    // Delete
    void delete(Long id);

}
