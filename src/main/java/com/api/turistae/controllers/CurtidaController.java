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

import com.api.turistae.dtos.CurtidaDTO;
import com.api.turistae.dtos.DadosCurtidaDTO;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.services.CurtidaService;
import com.api.turistae.utils.DataUtils;

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

    // HttpGet
    @GetMapping
    public List<DadosCurtidaDTO> getCurtidas() {
        logger.info("Get todas Curtidas.");
        return curtidaService.getAll();
    }

    @GetMapping("{id}")
    public DadosCurtidaDTO getCurtidaPorId(@PathVariable Long id) {
        logger.info("Get Curtida id: {}", id);
        return curtidaService.getById(id);
    }

    // HttpPost
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long postCurtida(@Valid @RequestBody CurtidaDTO curtidaDTO) {

        curtidaDTO.setDataCriacao(DataUtils.getDataAtualComMascara());
        curtidaDTO.setDataEdicao(DataUtils.getDataAtualComMascara());

        logger.info("Post Curtida: {}", curtidaDTO);

        // Retorno do cadastro
        Long id = 0l;
        try {
            id = curtidaService.post(curtidaDTO);
        } catch (DataIntegrityViolationException e) {
            throw new RegraNegocioException("Curtida indisponível.");
        }

        return id;
    }

    // HttpPut
    @PutMapping("{id}")
    public void putCurtida(@PathVariable Long id, @Valid @RequestBody CurtidaDTO curtidaDTO) {

        curtidaDTO.setDataEdicao(DataUtils.getDataAtualComMascara());

        logger.info("Put Curtida id {}: {}", id, curtidaDTO);

        try {
            curtidaService.put(id, curtidaDTO);
        } catch (DataIntegrityViolationException e) {
            throw new RegraNegocioException("Curtida indisponível.");
        }

    }

    // HttpDelete
    @DeleteMapping("{id}")
    public void deleteCurtida(@PathVariable Long id) {

        logger.info("Delete Curtida id {}", id);

        curtidaService.delete(id);
    }

}