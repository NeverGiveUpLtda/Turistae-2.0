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

import com.api.turistae.dtos.DadosImagemDTO;
import com.api.turistae.dtos.ImagemDTO;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.services.ImagemService;
import com.api.turistae.utils.ImagemUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/imagem")
public class ImagemController {

    // Atributos
    private ImagemService imagemService;
    private final Logger logger;

    // Construtor
    public ImagemController(ImagemService imagemService) {
        this.imagemService = imagemService;
        this.logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Imagem Controller iniciado.");
    }

    // HttpGet
    @GetMapping
    public List<DadosImagemDTO> getImagens() {
        logger.info("Get todas Imagens.");
        return imagemService.getAll();
    }

    @GetMapping("/turismo/{id}")
    public List<DadosImagemDTO> getImagensPorTurismo(@PathVariable Long id) {
        logger.info("Get todas Imagens por turismo id: {}", id);
        return imagemService.getByTurismo(id);
    }

    @GetMapping("{id}")
    public DadosImagemDTO getImagemPorId(@PathVariable Long id) {
        logger.info("Get Imagem id: {}", id);
        return imagemService.getById(id);
    }

    // HttpPost
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long postImagem(@Valid @RequestBody ImagemDTO imagemDTO) {

        //TODO
        String validacao = ImagemUtils.validarImagem(imagemDTO.getString64());
        // Valida a imagem
        if (!"valida".equals(validacao)) {
            throw new RegraNegocioException("Imagem inválida: " + validacao);
        }

        logger.info("Post Imagem: {}", imagemDTO);

        // Se Imagem já exisir na tabela, retornar erro
        // Retorno do cadastro

        Long id = 0l;
        try {
            id = imagemService.post(imagemDTO);
        } catch (DataIntegrityViolationException e) {
            throw new RegraNegocioException("Erro ao cadastrar Imagem.");
        }

        return id;
    }

    // HttpPut
    @PutMapping("{id}")
    public void putImagem(@PathVariable Long id, @Valid @RequestBody ImagemDTO imagemDTO) {

        logger.info("Put Imagem id {}: {}", id, imagemDTO);

        // Se Imagem já exisir na tabela, retornar erro
        // Retorno do cadastro
        try {
            imagemService.put(id, imagemDTO);
        } catch (DataIntegrityViolationException e) {
            throw new RegraNegocioException("Erro ao atualizar Imagem.");
        }
    }

    // HttpDelete
    @DeleteMapping("{id}")
    public void deleteImagem(@PathVariable Long id) {

        logger.info("Delete Imagem id {}", id);

        imagemService.delete(id);
    }

}
