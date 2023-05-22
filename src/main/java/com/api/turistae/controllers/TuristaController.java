package com.api.turistae.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
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

import com.api.turistae.dtos.DadosTuristaDTO;
import com.api.turistae.dtos.TuristaDTO;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.services.TuristaService;
import com.api.turistae.services.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/turista")
public class TuristaController {

    // Atributos
    private TuristaService turistaService;
    private UsuarioService usuarioService;
    private final Logger logger;

    // Construtor
    public TuristaController(TuristaService turistaService, UsuarioService usuarioService) {
        this.turistaService = turistaService;
        this.usuarioService = usuarioService;
        this.logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Turista Controller iniciado.");
    }

    @GetMapping("{id}")
    public DadosTuristaDTO getTuristaPorId(@PathVariable Long id) {
        logger.info("Get turista id: {}", id);
        return turistaService.getById(id);
    }

    // HttpPost
    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public Long postTurista(@Valid @RequestBody TuristaDTO turistaDTO) {

        logger.info("Post turista: {}", turistaDTO);

        // Retorno do cadastro
        Long id = 0l;

        try {
            turistaDTO.getUsuarioDTO().setId(usuarioService.post(turistaDTO.getUsuarioDTO()));
            id = turistaService.post(turistaDTO);
        } catch (DataIntegrityViolationException e) {

            if (e.getMessage().contains("REGISTRO_GERAL")) {
                throw new RegraNegocioException("RG já cadastrado em outra conta.");
            }

            if (e.getMessage().contains("CADASTRO_PESSOA_FISICA")) {
                throw new RegraNegocioException("CPF já cadastrado em outra conta.");
            }

            throw new RegraNegocioException("Erro ao cadastrar turista.");
        }

        return id;
    }

    // HttpPut
    @PutMapping
    public void putTurista(@Valid @RequestBody TuristaDTO turistaDTO, HttpServletRequest request) {

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION).substring(7);

        logger.info("Token: {}", authorizationHeader);

        logger.info("Put turista: {}", turistaDTO);

        try {
            usuarioService.put(turistaDTO);
            turistaService.put(turistaDTO);
        } catch (DataIntegrityViolationException e) {

            if (e.getMessage().contains("REGISTRO_GERAL")) {
                throw new RegraNegocioException("RG já cadastrado em outra conta.");
            }

            if (e.getMessage().contains("CADASTRO_PESSOA_FISICA")) {
                throw new RegraNegocioException("CPF já cadastrado em outra conta.");
            }

            throw new RegraNegocioException("Erro ao atualizar turista.");
        }

    }

    // HttpDelete
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTurista(@PathVariable Long id) {

        logger.info("Delete turista id {}", id);

        turistaService.delete(id);
    }

}
