package com.api.turistae.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.turistae.dtos.DadosCategoriaDTO;
import com.api.turistae.services.CategoriaService;


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
    @GetMapping("/get")
    public List<DadosCategoriaDTO> getCategorias() {
        logger.info("Get todas categorias.");
        return categoriaService.getAll();
    }

    @GetMapping("/get/{id}")
    public DadosCategoriaDTO getCategoriaPorId(@PathVariable Long id) {
        logger.info("Get categoria id: {}", id);
        return categoriaService.getById(id);
    }

}
