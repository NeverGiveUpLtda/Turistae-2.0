package com.api.turistae.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.turistae.dtos.CurtidaDTO;
import com.api.turistae.dtos.DadosCurtidaDTO;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.services.CurtidaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/curtida")
public class CurtidaController {

    // Atributos
    private CurtidaService curtidaService;
    private final Logger logger;

    // Construtor
    public CurtidaController(CurtidaService curtidaService) {
        this.curtidaService = curtidaService;
        this.logger = LoggerFactory.getLogger(getClass());
        logger.info("Curtida Controller iniciado.");
    }

    @GetMapping("/get/turismo/{id}")
    public int getQuantidadeCurtidas(@PathVariable Long id) {
        logger.info("Get quantidade de curtidas por turismo: {}", id);
        return curtidaService.getByTurismo(id).size();
    }

    @GetMapping("/get/{id}")
    public DadosCurtidaDTO getCurtidaPorId(@PathVariable Long id) {
        logger.info("Get Curtida id: {}", id);
        return curtidaService.getById(id);
    }

    @PostMapping("/curtir")
    @ResponseStatus(HttpStatus.CREATED)
    public Long curtir(@Valid @RequestBody CurtidaDTO curtidaDTO) {

        logger.info("Curtir: {}", curtidaDTO);

        // Retorno do cadastro
        Long id = 0l;

        try {
            id = curtidaService.getCurtidaByTurismoAndTurista(curtidaDTO.getTuristaId(), curtidaDTO.getTurismoId())
                    .getId();
        } catch (RegraNegocioException e) {
            logger.info("Curtida não existente.");
        }

        if (id != 0l) {
            throw new RegraNegocioException("Curtida já existe.");
        }

        try {
            id = curtidaService.post(curtidaDTO);
        } catch (DataIntegrityViolationException e) {
            throw new RegraNegocioException("Curtida inválida.");
        }

        return id;
    }

    @PostMapping("/descurtir")
    @ResponseStatus(HttpStatus.CREATED)
    public void descurtir(@Valid @RequestBody CurtidaDTO curtidaDTO) {

        logger.info("Descurtir: {}", curtidaDTO);

        // Retorno do cadastro
        Long id = 0l;
        try {
            id = curtidaService.getCurtidaByTurismoAndTurista(curtidaDTO.getTuristaId(), curtidaDTO.getTurismoId())
                    .getId();
        } catch (DataIntegrityViolationException e) {
            throw new RegraNegocioException("Descurtida inválida.");
        }

        curtidaService.delete(id);

    }

}
