package com.api.turistae.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.turistae.dtos.DadosVoucherDTO;
import com.api.turistae.dtos.VoucherDTO;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.services.VoucherService;
import com.api.turistae.utils.DataUtils;
import com.api.turistae.utils.VoucherUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/voucher")
public class VoucherController {

    /**
     *
     */
    private static final String VOUCHER_INDISPONIVEL = "Voucher indisponível.";
    // Atributos
    private VoucherService voucherService;
    private final Logger logger;

    // Construtor
    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
        this.logger = LoggerFactory.getLogger(getClass());
        logger.info("Voucher Controller iniciado.");
    }

    // HttpGet
    @GetMapping
    public List<DadosVoucherDTO> getVouchers() {
        logger.info("Get todos Vouchers.");
        return voucherService.getAll();
    }

    @GetMapping("/turismo/{turismoId}")
    public List<DadosVoucherDTO> getVouchersPorTurismo(@PathVariable Long turismoId) {
        logger.info("Get todos Vouchers para o turismo id: {}", turismoId);
        return voucherService.getVouchersPorTurismo(turismoId);
    }

    @GetMapping("/turismo/{turismoId}/unclaimed")
    public List<DadosVoucherDTO> getVouchersPorTurismoSemUsuario(@PathVariable Long turismoId) {
        logger.info("Get todos Vouchers sem usuário para o turismo id: {}", turismoId);
        return voucherService.getVouchersSemUsuario(turismoId);
    }

    @GetMapping("/turismo/{turismoId}/claimed")
    public List<DadosVoucherDTO> getVouchersPorTurismoComUsuario(@PathVariable Long turismoId) {
        logger.info("Get todos Vouchers com usuário para o turismo id: {}", turismoId);
        return voucherService.getVouchersComUsuario(turismoId);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<DadosVoucherDTO> getVouchersPorUsuario(@PathVariable Long usuarioId) {
        logger.info("Get todos Vouchers do usuário id: {}", usuarioId);
        return voucherService.getVouchersDoUsuario(usuarioId);
    }

    @GetMapping("{id}")
    public DadosVoucherDTO getVoucherPorId(@PathVariable Long id) {
        logger.info("Get Voucher id: {}", id);
        return voucherService.getById(id);
    }

    // HttpPost
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long postVoucher(@Valid @RequestBody VoucherDTO voucherDTO) {

        //TODO
        voucherDTO.setDataCriacao(DataUtils.getDataAtualComMascara());
        voucherDTO.setDataEdicao(DataUtils.getDataAtualComMascara());
        voucherDTO.setCodigo(VoucherUtils.gerarVoucher(voucherDTO.getTurismoId()));

        logger.info("Post Voucher: {}", voucherDTO);

        // Retorno do cadastro
        Long id = 0l;
        try {
            id = voucherService.post(voucherDTO);
        } catch (DataIntegrityViolationException e) {
            throw new RegraNegocioException(VOUCHER_INDISPONIVEL);
        }

        return id;
    }


    @PostMapping("/claim")
    public void claimVoucher(@Valid @RequestBody VoucherDTO voucherDTO) {

        //TODO
        voucherDTO.setDataEdicao(DataUtils.getDataAtualComMascara());

        logger.info("Claim Voucher: {}", voucherDTO);

        try {
            voucherService.claim(voucherDTO);
        } catch (DataIntegrityViolationException e) {
            throw new RegraNegocioException(VOUCHER_INDISPONIVEL);
        }
    }

    // HttpPut
    @PutMapping("{id}")
    public void putVoucher(@PathVariable Long id, @Valid @RequestBody VoucherDTO voucherDTO) {

        //TODO
        voucherDTO.setDataEdicao(DataUtils.getDataAtualComMascara());
        voucherDTO.setCodigo(VoucherUtils.gerarVoucher(voucherDTO.getTurismoId()));

        logger.info("Put Voucher id {}: {}", id, voucherDTO);

        try {
            voucherService.put(id, voucherDTO);
        } catch (DataIntegrityViolationException e) {
            throw new RegraNegocioException(VOUCHER_INDISPONIVEL);
        }

    }

    // HttpDelete
    @DeleteMapping("{id}")
    public void deleteVoucher(@PathVariable Long id) {

        logger.info("Delete Voucher id {}", id);

        voucherService.delete(id);
    }

}
