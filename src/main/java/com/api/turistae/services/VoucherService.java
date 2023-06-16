package com.api.turistae.services;

import java.util.List;

import com.api.turistae.dtos.DadosVoucherDTO;
import com.api.turistae.dtos.VoucherDTO;

public interface VoucherService {

    // Create
    Long post(VoucherDTO dto);

    // Read
    List<DadosVoucherDTO> getAll();

    List<DadosVoucherDTO> getVouchersSemUsuario(Long id);

    List<DadosVoucherDTO> getVouchersComUsuario(Long id);

    List<DadosVoucherDTO> getVouchersPorTurismo(Long id);

    List<DadosVoucherDTO> getVouchersDoUsuario(Long id);

    DadosVoucherDTO getById(Long id);

    DadosVoucherDTO claim(VoucherDTO dto);

    // Update
    void put(Long id, VoucherDTO dto);

    // Delete
    void delete(Long id);

}
