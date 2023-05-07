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

import com.api.turistae.dtos.DadosTurismoDTO;
import com.api.turistae.dtos.TurismoDTO;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.services.TurismoService;
import com.api.turistae.utils.DataUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/turismo")
public class TurismoController {
	
    // Atributos
    private TurismoService turismoService;
    private final Logger logger;

    // Construtor
    public TurismoController(TurismoService turismoService) {
        this.turismoService = turismoService;
        this.logger = LoggerFactory.getLogger(TurismoController.class);
        logger.info("Turismo Controller iniciado.");
    }
    
    // HttpGet
    @GetMapping
    public List<DadosTurismoDTO> getTurismos() {
        logger.info("Get todos turismos.");
        return turismoService.getAll();
    }

    @GetMapping("{id}")
    public DadosTurismoDTO getTurismoPorId(@PathVariable Long id) {
        logger.info("Get turismo id: {}", id);
        return turismoService.getById(id);
    }

    // HttpPost
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long postTurismo(@Valid @RequestBody TurismoDTO turismoDTO) {
        turismoDTO.setDataCriacao(DataUtils.getDataAtualComMascara());
        turismoDTO.setDataEdicao(DataUtils.getDataAtualComMascara());

        logger.info("Post turismo: {}", turismoDTO);

        //  Se turismo já exisir na tabela, retornar erro
        // Retorno do cadastro
        Long id = 0l;
        try {
            id = turismoService.post(turismoDTO);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("cadastro_nacional_pessoa_juridica_UNIQUE")) {
                throw new RegraNegocioException("CNPJ indisponível.");
            } else {
                throw new RegraNegocioException("Erro ao cadastrar turismo.");
            }
        }

        return id;
    }

    //  HttpPut
    @PutMapping("{id}")
    public void putTurismo(@PathVariable Long id, @Valid @RequestBody TurismoDTO turismoDTO) {

        turismoDTO.setDataEdicao(DataUtils.getDataAtualComMascara());

        logger.info("Put turismo id {}: {}", id, turismoDTO);

        //  Se turismo já exisir na tabela, retornar erro
        // Retorno do cadastro
        try {
            turismoService.put(id, turismoDTO);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("cadastro_nacional_pessoa_juridica_UNIQUE")) {
                throw new RegraNegocioException("CNPJ indisponível.");
            } else {
                throw new RegraNegocioException("Erro ao atualizar turismo.");
            }
        }
    }
    
    // HttpDelete
    @DeleteMapping("{id}")
    public void deleteTurismo(@PathVariable Long id) {

        logger.info("Delete turismo id {}", id);

        turismoService.delete(id);
    }
    
}