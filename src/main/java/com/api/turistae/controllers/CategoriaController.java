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

import com.api.turistae.dtos.CategoriaDTO;
import com.api.turistae.dtos.DadosCategoriaDTO;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.services.CategoriaService;
import com.api.turistae.utils.DataUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    // Atributos
    private CategoriaService categoriaService;
    private final Logger logger;

    // Construtor
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
        this.logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Categoria Controller iniciado.");
    }

    // HttpGet
    @GetMapping
    public List<DadosCategoriaDTO> getCategorias() {
        logger.info("Get todas categorias.");
        return categoriaService.getAll();
    }

    @GetMapping("{id}")
    public DadosCategoriaDTO getCategoriaPorId(@PathVariable Long id) {
        logger.info("Get categoria.");
        return categoriaService.getById(id);
    }

    // HttpPost
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long postCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {

        //TODO
        categoriaDTO.setDataCriacao(DataUtils.getDataAtualComMascara());
        categoriaDTO.setDataEdicao(DataUtils.getDataAtualComMascara());

        logger.info("Post categoria.");

        // Retorno do cadastro
        Long id = 0l;
        try {
            id = categoriaService.post(categoriaDTO);
        } catch(DataIntegrityViolationException e) {
            throw new RegraNegocioException("Categoria indisponível.");
        }

        return id;
    }

    // HttpPut
    @PutMapping("{id}")
    public void putCategoria(@PathVariable Long id, @Valid @RequestBody CategoriaDTO categoriaDTO) {

        //TODO
        categoriaDTO.setDataEdicao(DataUtils.getDataAtualComMascara());

        logger.info("Put categoria.");

        try {
            categoriaService.put(id, categoriaDTO);
        } catch(DataIntegrityViolationException e) {
            throw new RegraNegocioException("Categoria indisponível.");
        }

    }

    // HttpDelete
    @DeleteMapping("{id}")
    public void deleteCategoria(@PathVariable Long id) {

        logger.info("Delete categoria.");

        categoriaService.delete(id);
    }

}
